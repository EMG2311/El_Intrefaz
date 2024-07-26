package com.elintefaz.El_intefaz.controller;

import com.elintefaz.El_intefaz.dto.CategoryDto;
import com.elintefaz.El_intefaz.service.implementation.CategoriaServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoriaServiceImplementation categoriaServiceImplementation;
    private static final Logger log= LoggerFactory.getLogger("logToElkAppender");
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto requestCategoryDto) {
        try{
            CategoryDto categoryDto=categoriaServiceImplementation.createCategory(requestCategoryDto);
            if(categoryDto==null){
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok(categoryDto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/toList")
    public ResponseEntity<List<CategoryDto>> toListCategories(){
        try {
            List<CategoryDto> categoryDtos = categoriaServiceImplementation.toListCategory();
            return ResponseEntity.ok(categoryDtos);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        try{
            categoriaServiceImplementation.deleteCategory(id);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            return  ResponseEntity.status(500).body(null);
        }
    }
}
