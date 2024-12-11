package com.elintefaz.El_intefaz.controller;

import com.elintefaz.El_intefaz.dto.ProductUpdateDto;
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
@CrossOrigin(origins = "http://localhost:5173/")
public class ProductController {

    @Autowired
    private ProductsServiceImplementation productsServiceImplementation;

    @PostMapping("/add")
    public ResponseEntity<ProductsDto> addProduct(@RequestBody @Validated ProductsDto productsDtoRequest){
        ProductsDto productsDto=productsServiceImplementation.addProduct(productsDtoRequest);
        if(productsDto==null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(productsDto);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ProductsDto>> getProducts(){
        try {
            List<ProductsDto> list = productsServiceImplementation.getProducts();
            return ResponseEntity.ok(list);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/view/{name}")
    public ResponseEntity<ProductsDto>getProduct(@PathVariable @Validated String name){
        try{
            ProductsDto productsDto = productsServiceImplementation.getProduct(name);
            if(productsDto==null){
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok(productsDto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/view/category/{name}")
    public ResponseEntity<List<ProductsDto>> getProductByCategory(@PathVariable String name){
        try {
            List<ProductsDto> list = productsServiceImplementation.getProductsByCategory(name);
            if (list.isEmpty()) {
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok(list);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ProductUpdateDto> updateProductByName(@RequestBody @Validated ProductUpdateDto productUpdateRequest){
        try {
            ProductUpdateDto productUpdateDto = productsServiceImplementation.updateProduct(productUpdateRequest);

            if (productUpdateDto == null) {
                ResponseEntity.status(400).body(productUpdateRequest);
            }
            return ResponseEntity.ok(productUpdateDto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
    @PatchMapping("/addStock/{name}/{stock}")
    public ResponseEntity<ProductsDto> addStock(@PathVariable @Validated String name, @PathVariable @Validated Integer stock){
        try {
            ProductsDto productDto = productsServiceImplementation.addStockProducts(name, stock);
            if (productDto == null) {
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok(productDto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @PatchMapping("/deleteStock/{name}/{stock}")
    public ResponseEntity<ProductsDto> deleteStock(@PathVariable @Validated String name, @PathVariable @Validated Integer stock){
        try {
            ProductsDto productDto = productsServiceImplementation.deleteStockProducts(name, stock);
            if (productDto == null) {
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok(productDto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<ProductsDto> delateProduct(@PathVariable @Validated String name){
        try {
            ProductsDto productsDto=productsServiceImplementation.delateProducts(name);
            if (productsDto==null){
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok(productsDto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
}
