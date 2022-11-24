package com.elintefaz.El_intefaz.controller;

import com.elintefaz.El_intefaz.dto.CategoryDto;
import com.elintefaz.El_intefaz.service.implementation.CategoriaServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoriaServiceImplementation categoriaServiceImplementation;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody@Validated CategoryDto categoryDto) {
        return ResponseEntity.ok(categoriaServiceImplementation.CreateCategory(categoryDto));
    }
}
