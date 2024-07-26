package com.elintefaz.El_intefaz.service;

import com.elintefaz.El_intefaz.dto.CategoryDto;
import com.elintefaz.El_intefaz.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> toListCategory();
    void deleteCategory(Integer id);


}