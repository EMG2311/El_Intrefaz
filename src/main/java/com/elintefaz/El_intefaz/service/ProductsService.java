package com.elintefaz.El_intefaz.service;

import com.elintefaz.El_intefaz.dto.ProductViewDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductsService {
    ProductsDto addProduct(ProductsDto productsDto);

    List<ProductViewDto> getProducts();

    ProductsDto updateProductById(Integer id, ProductsDto productsDto);

    ProductsDto addStockProducts(Integer id,Integer stock);

    ProductsDto delateProducts(Integer id);
}
