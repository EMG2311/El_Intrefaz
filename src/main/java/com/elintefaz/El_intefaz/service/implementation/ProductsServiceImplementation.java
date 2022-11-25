package com.elintefaz.El_intefaz.service.implementation;

import com.elintefaz.El_intefaz.dto.ProductViewDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.mapper.ProductsMapper;
import com.elintefaz.El_intefaz.model.Products;
import com.elintefaz.El_intefaz.repository.CategoryRepository;
import com.elintefaz.El_intefaz.repository.ProductsRepository;
import com.elintefaz.El_intefaz.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsServiceImplementation implements ProductsService {

    private final ProductsRepository productsRepository;
    private final ProductsMapper productsMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductsDto addProduct(ProductsDto productsDto) {
        Products products=productsMapper.converToEntity(productsDto);
        products.setCategory(categoryRepository.findById(productsDto.getCategory()).get());
        products.setHighDate(new Date());
        productsRepository.save(products);
        return productsDto;
    }

    @Override
    public List<ProductViewDto> getProducts() {
        List<ProductViewDto> a= new ArrayList<>();
        for (Products p:productsRepository.findAll()) {
            ProductViewDto productViewDto=productsMapper.converToViewDto(p);
            productViewDto.setCategory( categoryRepository.findById(p.getCategory().getIdCategory()).get().getName());
            a.add(productViewDto);
        }
        return a;
    }

    @Override
    public ProductsDto updateProductById(Integer id, ProductsDto productsDto) {
       Products products=productsRepository.findById(id).get();
       products.setName(productsDto.getName());
       products.setStock(productsDto.getStock());
       products.setPrice(productsDto.getPrice());
       products.setCategory(categoryRepository.findById(productsDto.getCategory()).get());
       productsRepository.save(products);
        return productsDto;
    }

    @Override
    public ProductsDto addStockProducts(Integer id,Integer stock) {
        Products products=productsRepository.findById(id).get();
        products.setStock(products.getStock()+stock);
        ProductsDto productsDto = productsMapper.converToDto(products);
        productsRepository.save(products);
        return productsDto;
    }

    public ProductsDto delateProducts(Integer id){
        ProductsDto productsDto=productsMapper.converToDto(productsRepository.findById(id).get());
        productsRepository.deleteById(id);
        return productsDto;
    }


}
