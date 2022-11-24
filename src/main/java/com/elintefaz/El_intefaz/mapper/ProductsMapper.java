package com.elintefaz.El_intefaz.mapper;

import com.elintefaz.El_intefaz.dto.ProductViewDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.model.Products;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsMapper {

    private final ModelMapper mapper;

    public Products converToEntity(ProductsDto productsDto){
        return mapper.map(productsDto,Products.class);
    }

    public ProductsDto converToDto(Products products){ return mapper.map(products,ProductsDto.class);}

    public ProductViewDto converToViewDto(Products products){return mapper.map(products,ProductViewDto.class);}

}
