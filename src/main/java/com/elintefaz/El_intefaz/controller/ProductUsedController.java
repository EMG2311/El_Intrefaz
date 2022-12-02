package com.elintefaz.El_intefaz.controller;

import com.elintefaz.El_intefaz.dto.ProductUsedDto;
import com.elintefaz.El_intefaz.dto.ProductUsedViewDto;
import com.elintefaz.El_intefaz.model.ProductUsed;
import com.elintefaz.El_intefaz.service.implementation.ProductUsedServiceImplementation;
import com.elintefaz.El_intefaz.service.implementation.ProductsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productUsed")
public class ProductUsedController {
    @Autowired
    private ProductUsedServiceImplementation productUsedServiceImplementation;
    @PostMapping("/add")
    public ResponseEntity<ProductUsedDto> addProduct(@RequestBody @Validated ProductUsedDto productUsedDto){
        return ResponseEntity.ok(productUsedServiceImplementation.addProduct(productUsedDto));
    }

    @GetMapping("/view/{idOrder}")
    public ResponseEntity<List<ProductUsedViewDto>> viewProduct(@PathVariable @Validated Integer idOrder){
        return ResponseEntity.ok(productUsedServiceImplementation.viewProduct(idOrder));
    }

    @PutMapping("/update/{idProductUsed}")
    public ResponseEntity<ProductUsedDto> updateProduct(@PathVariable @Validated Integer idProductUsed, @RequestBody @Validated ProductUsedDto productUsedDto){
        return ResponseEntity.ok(productUsedServiceImplementation.updateProduct(idProductUsed,productUsedDto));
    }

    @DeleteMapping("/delate/{id}")
    public ResponseEntity<ProductUsedDto> deleteProduct(@PathVariable @Validated Integer id){
        return ResponseEntity.ok(productUsedServiceImplementation.deleteProduct(id));
    }



}
