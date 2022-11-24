package com.elintefaz.El_intefaz.mapper;

import com.elintefaz.El_intefaz.dto.CategoryDto;
import com.elintefaz.El_intefaz.model.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper mapper;

    public Category converToEntity(CategoryDto categoryDto){
        return mapper.map(categoryDto,Category.class);
    }
}
