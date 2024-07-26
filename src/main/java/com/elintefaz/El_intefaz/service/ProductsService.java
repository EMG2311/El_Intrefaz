package com.elintefaz.El_intefaz.service;

import com.elintefaz.El_intefaz.dto.ProductUpdateDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.model.Products;

import java.util.List;

public interface ProductsService {
    ProductsDto addProduct(ProductsDto productsDto);

    List<ProductsDto> getProducts();

    ProductsDto getProduct(String name);

    List<ProductsDto> getProductsByCategory(String name);

    ProductUpdateDto updateProduct(ProductUpdateDto productsDto);

    ProductsDto addStockProducts(String name,Integer stock);
    ProductsDto deleteStockProducts(String name,Integer stock);

    ProductsDto delateProducts(String name);


}
