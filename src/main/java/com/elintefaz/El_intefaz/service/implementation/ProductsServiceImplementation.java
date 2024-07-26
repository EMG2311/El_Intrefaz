package com.elintefaz.El_intefaz.service.implementation;

import com.elintefaz.El_intefaz.dto.ProductUpdateDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.exception.ProductException;
import com.elintefaz.El_intefaz.model.Products;
import com.elintefaz.El_intefaz.repository.CategoryRepository;
import com.elintefaz.El_intefaz.repository.ProductsRepository;
import com.elintefaz.El_intefaz.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsServiceImplementation implements ProductsService {

    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;

    private final static Logger LOGGER=  LoggerFactory.getLogger(ProductsServiceImplementation.class);

    @Override
    public ProductsDto addProduct(ProductsDto productsDto) {
        try{
            if(productsDto.getStock()<0 || productsDto.getPrice()<0){
                throw new ProductException();
            }
            LOGGER.info("----------Guardando producto----------");
            productsRepository.save(Products.builder()
                            .name(productsDto.getName())
                            .stock(productsDto.getStock())
                            .price(productsDto.getPrice())
                            .category(categoryRepository.findByName(productsDto.getCategory()).get())
                            .build());
            return productsDto;
        }catch (ProductException p){
            LOGGER.error("Error al agregar nuevo producto, el stock y/o el precio no pueden ser menor a 0");
            return null;
        }
        catch (Exception e){
            LOGGER.error("Error inesperado al crear producto");
            return null;
        }
    }

    @Override
    public List<ProductsDto> getProducts() {
        try {
            LOGGER.info("----------Obteniendo todos los productos----------");
            List<ProductsDto> list = productsRepository.findAll().stream().map(products -> {
                return ProductsDto.builder()
                        .stock(products.getStock())
                        .name(products.getName())
                        .category(products.getCategory().getName())
                        .price(products.getPrice())
                        .build();
            }).collect(Collectors.toList());
            return list;
        }
        catch (Exception e){
            LOGGER.error("Error inesperado "+e.getMessage());
            return null;
        }
    }

    @Override
    public ProductsDto getProduct(String name) {
        try {
            LOGGER.info("Buscando producto " + name);
            Products products = productsRepository.findByName(name)
                        .orElseThrow(()->new EntityNotFoundException("El producto "+ name +" no existe"));

            return ProductsDto.builder()
                    .name(products.getName())
                    .stock(products.getStock())
                    .price(products.getPrice())
                    .category(products.getCategory().getName())
                    .build();
        }catch (EntityNotFoundException nf){
            LOGGER.error(nf.getMessage());
            return null;
        }
        catch (Exception e){
            LOGGER.error("Error inesperado al buscar el producto "+name+" "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProductsDto> getProductsByCategory(String name) {
        try {
            List<ProductsDto> list = productsRepository.findByCategory(categoryRepository.findByName(name).get()).get().stream().map(products -> {
                return ProductsDto.builder()
                        .stock(products.getStock())
                        .name(products.getName())
                        .category(products.getCategory().getName())
                        .price(products.getPrice())
                        .build();
            }).collect(Collectors.toList());
            return list;
        }catch (Exception e){
            LOGGER.error("Error inesperado al buscar producto por categoria "+e.getMessage());
            return null;
        }
    }

    public Products getProductById(Integer id){
        try {
            return productsRepository.findById(id)
                    .orElseThrow(()->new EntityNotFoundException("El producto con id "+ id+" no existe"));
        }catch (EntityNotFoundException nf){
            LOGGER.error(nf.getMessage());
            return null;
        }catch (Exception e){
            LOGGER.error("Error inesperado al buscar por el id " +id);
            return  null;
        }
    }

    @Override
    public ProductUpdateDto updateProduct(ProductUpdateDto productDto) {
        try {
            if (productDto.getStock() < 0 || productDto.getPrice() < 0) {
                throw new ProductException("El stock y/o el precio no pueden ser menor a 0");
            }
            LOGGER.info("----------Obteniendo producto para actualizar----------");
            Products products = productsRepository.findByName(productDto.getName())
                    .orElseThrow(()->new EntityNotFoundException("El producto "+ productDto.getName()+" no existe"));
            products.setName(productDto.getNewName().equals("") ? products.getName() : productDto.getNewName());
            products.setStock(productDto.getStock().equals(0) ? products.getStock() : productDto.getStock());
            products.setPrice(productDto.getPrice().equals(0) ? products.getPrice() : productDto.getPrice());
            products.setCategory(productDto.getCategory().equals("") ? products.getCategory() : categoryRepository.findByName(productDto.getName()).get());
            productsRepository.save(products);
            return productDto;
        }catch (EntityNotFoundException nf){
            LOGGER.error(nf.getMessage());
            return null;
        }catch (ProductException p){
            LOGGER.error("Error al actualizar el producto "+productDto.getName()+" "+p.getMessage());
            return null;
        }
        catch (Exception e){
            LOGGER.error("Error inesperado al actualizar producto "+e.getMessage());
            return null;
        }
    }

    @Override
    public ProductsDto addStockProducts(String name,Integer stock) {
        try {
            if (stock < 0) {
                throw new ProductException("El stock no puede ser menor a 0");
            }
            LOGGER.info("----------Obteniendo producto para agregar stock----------");
            Products product = productsRepository.findByName(name)
                    .orElseThrow(()->new EntityNotFoundException("El producto "+ name+" no existe"));
            product.setStock(product.getStock() + stock);
            ProductsDto productsDto = ProductsDto.builder()
                            .name(product.getName())
                            .stock(product.getStock())
                            .price(product.getPrice())
                            .category(product.getCategory().getName()).build();
            LOGGER.info("----------Guardando stock----------");
            productsRepository.save(product);
            return productsDto;
        }catch (EntityNotFoundException nf){
            LOGGER.error(nf.getMessage());
            return null;
        }catch (ProductException p){
            LOGGER.error(p.getMessage());
            return null;
        }catch (Exception e){
            LOGGER.error("Error inersperado al agregar stock al producto "+name+" "+e.getMessage());
            return null;
        }
    }

    @Override
    public ProductsDto deleteStockProducts(String name, Integer stock) {
        try {
            if (stock < 0) {
                throw new ProductException(name + " El stock ingresado tiene que ser un numero positivo");
            }
            LOGGER.info("----------Obteniendo producto para eliminar stock----------");
            Products product = productsRepository.findByName(name)
                    .orElseThrow(()->new EntityNotFoundException("El producto "+ name +" no existe"));
            product.setStock(product.getStock() - stock);
            if(product.getStock()<0){
                throw new ProductException("El resultado de stock no puede ser menor a 0");
            }
            ProductsDto productsDto = ProductsDto.builder()
                    .name(product.getName())
                    .stock(product.getStock())
                    .price(product.getPrice())
                    .category(product.getCategory().getName()).build();
            LOGGER.info("----------Guardando stock----------");
            productsRepository.save(product);
            return productsDto;
        }catch (EntityNotFoundException nf){
            LOGGER.error(nf.getMessage());
            return null;
        }catch (ProductException p){
            LOGGER.error(p.getMessage());
            return null;
        }catch (Exception e){
            LOGGER.error("Error inersperado al agregar stock al producto "+name+" "+e.getMessage());
            return null;
        }
    }
    @Transactional
    public ProductsDto delateProducts(String name){
        try{
            LOGGER.info("----------Buscando producto a eliminar----------");
            Products product = productsRepository.findByName(name)
                    .orElseThrow(()->new EntityNotFoundException("El producto "+ name +" no existe"));
            ProductsDto productsDto = ProductsDto.builder()
                    .name(product.getName())
                    .stock(product.getStock())
                    .price(product.getPrice())
                    .category(product.getCategory().getName()).build();
            productsRepository.deleteByName(name);
            return productsDto;
        }catch (EntityNotFoundException nf){
            LOGGER.error(nf.getMessage());
            return null;
        }catch (Exception e){
            LOGGER.error("Error inesperado al eliminar un producto "+e.getMessage());
            return null;
        }
    }


}
