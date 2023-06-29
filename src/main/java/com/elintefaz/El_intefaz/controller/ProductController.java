package com.elintefaz.El_intefaz.controller;

import com.elintefaz.El_intefaz.dto.ProductViewDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.service.implementation.ProductsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductsServiceImplementation productsServiceImplementation;

    @PostMapping("/add")
    public ResponseEntity<ProductsDto> addProduct(@RequestBody @Validated ProductsDto productsDto){
        return ResponseEntity.ok(productsServiceImplementation.addProduct(productsDto));
    }

    @CrossOrigin
    @GetMapping("/view")
    public ResponseEntity<List<ProductViewDto>> getProduct(){
        return ResponseEntity.ok(productsServiceImplementation.getProducts());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductsDto> updateProductById(@PathVariable @Validated Integer id, @RequestBody @Validated ProductsDto productsDto){
        return ResponseEntity.ok(productsServiceImplementation.updateProductById(id, productsDto));
    }
    @PatchMapping("/addStock/{id}/{stock}")
    public ResponseEntity<ProductsDto> addStock(@PathVariable @Validated Integer id, @PathVariable @Validated Integer stock){
        return ResponseEntity.ok(productsServiceImplementation.addStockProducts(id,stock));
    }

    @DeleteMapping("/delate/{id}")
    public ResponseEntity<ProductsDto> delateProduct(@PathVariable @Validated Integer id){
        return ResponseEntity.ok(productsServiceImplementation.delateProducts(id));
    }
}
