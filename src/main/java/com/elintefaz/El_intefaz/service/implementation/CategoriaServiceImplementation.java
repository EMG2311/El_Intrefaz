package com.elintefaz.El_intefaz.service.implementation;

import com.elintefaz.El_intefaz.dto.CategoryDto;
import com.elintefaz.El_intefaz.mapper.CategoryMapper;
import com.elintefaz.El_intefaz.model.Category;
import com.elintefaz.El_intefaz.repository.CategoryRepository;
import com.elintefaz.El_intefaz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriaServiceImplementation implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryDto CreateCategory(CategoryDto categoryDto) {
        Category category=categoryMapper.converToEntity(categoryDto);
        categoryRepository.save(category);
        return categoryDto;
    }
}
