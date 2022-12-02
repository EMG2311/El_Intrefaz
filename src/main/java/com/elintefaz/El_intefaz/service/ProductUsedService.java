package com.elintefaz.El_intefaz.service;

import com.elintefaz.El_intefaz.dto.ProductUsedDto;
import com.elintefaz.El_intefaz.dto.ProductUsedViewDto;
import com.elintefaz.El_intefaz.model.ProductUsed;

import java.util.List;

public interface ProductUsedService {
    ProductUsedDto addProduct(ProductUsedDto productUsedDto);
    List<ProductUsedViewDto> viewProduct(Integer idOrder);
    ProductUsedDto updateProduct(Integer idProductUsed,ProductUsedDto productUsedDto);

    ProductUsedDto deleteProduct(Integer idProductUsed);

}
