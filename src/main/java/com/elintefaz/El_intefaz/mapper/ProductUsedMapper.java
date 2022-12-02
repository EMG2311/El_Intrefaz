package com.elintefaz.El_intefaz.mapper;

import com.elintefaz.El_intefaz.dto.ProductUsedDto;
import com.elintefaz.El_intefaz.model.ProductUsed;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductUsedMapper {
    @Autowired
    private final ModelMapper mapper;

    public ProductUsedDto convertToDto(ProductUsed productUsed){return mapper.map(productUsed,ProductUsedDto.class);}

    public ProductUsed convertToEntity(ProductUsedDto productUsedDto){return mapper.map(productUsedDto,ProductUsed.class);}


}
