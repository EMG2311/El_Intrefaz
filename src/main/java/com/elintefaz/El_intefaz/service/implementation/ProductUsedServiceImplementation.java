package com.elintefaz.El_intefaz.service.implementation;

import com.elintefaz.El_intefaz.dto.ProductUsedDto;
import com.elintefaz.El_intefaz.dto.ProductUsedViewDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.mapper.ProductUsedMapper;
import com.elintefaz.El_intefaz.model.Order;
import com.elintefaz.El_intefaz.model.ProductUsed;
import com.elintefaz.El_intefaz.model.Products;
import com.elintefaz.El_intefaz.repository.OrderRepository;
import com.elintefaz.El_intefaz.repository.ProductUsedRepository;
import com.elintefaz.El_intefaz.repository.ProductsRepository;
import com.elintefaz.El_intefaz.service.ProductUsedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUsedServiceImplementation implements ProductUsedService {

    private final ProductUsedRepository productUsedRepository;

    private final ProductUsedMapper productUsedMapper;
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;
    @Override
    public ProductUsedDto addProduct(ProductUsedDto productUsedDto) {
        ProductUsed productUsed=new ProductUsed();
        productUsed.setAmount(productUsedDto.getAmount());
        Products products=productsRepository.findById(productUsedDto.getIdproduct()).get();
        productUsed.setProduct(products);
        productUsed.setNOrder(orderRepository.findById(productUsedDto.getIdOrder()).get());
        productUsed.setPay(productUsed.getAmount()*products.getPrice());
        productUsedRepository.save(productUsed);
        return productUsedDto;
    }


    public List<ProductUsedViewDto> viewProduct(Integer idOrder){
        List<ProductUsed> productUsed=productUsedRepository.findBynOrder(orderRepository.findById(idOrder).get());
        List<ProductUsedViewDto> productView=new ArrayList<>();
        for (ProductUsed p:productUsed) {
            ProductUsedViewDto productUsedViewDto=new ProductUsedViewDto();
            String nameProduct=productsRepository.findById(p.getProduct().getProductId()).get().getName();
            productUsedViewDto.setProduct(nameProduct);
            productUsedViewDto.setAmount(p.getAmount());
            productUsedViewDto.setPay(p.getPay());
            productView.add(productUsedViewDto);
        }

        return productView;
    }

    public ProductUsedDto updateProduct(Integer idProductUsed,ProductUsedDto productUsedDto){
        ProductUsed productUsed=productUsedRepository.findById(idProductUsed).get();
        Products products = productsRepository.findById(productUsedDto.getIdproduct()).get();
        productUsed.setPay(productUsedDto.getAmount()*products.getPrice());
        productUsed.setProduct(products);
        productUsed.setNOrder(orderRepository.findById(productUsedDto.getIdOrder()).get());
        productUsedRepository.save(productUsed);
        return productUsedDto;


    }


    public ProductUsedDto deleteProduct(Integer idProductUsed){
        ProductUsedDto productUsed=productUsedMapper.convertToDto(productUsedRepository.findById(idProductUsed).get());
        productUsedRepository.deleteById(idProductUsed);
        return productUsed;
    }



}