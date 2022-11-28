package com.elintefaz.El_intefaz.controller;

import com.elintefaz.El_intefaz.dto.OrderDto;
import com.elintefaz.El_intefaz.service.implementation.OrderServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImplementation orderServiceImplementation;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Validated OrderDto orderDto){
        return ResponseEntity.ok(orderServiceImplementation.createOrder(orderDto));
    }


}
