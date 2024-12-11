package com.elintefaz.El_intefaz.controller;

import com.elintefaz.El_intefaz.Request.EmailRequest;
import com.elintefaz.El_intefaz.Response.OrderResponse;
import com.elintefaz.El_intefaz.Response.OrderUpdateResponse;
import com.elintefaz.El_intefaz.dto.OrderDto;
import com.elintefaz.El_intefaz.dto.OrderUpdateDto;
import com.elintefaz.El_intefaz.service.implementation.OrderServiceImplementation;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173/")
public class OrderController {
    @Autowired
    private OrderServiceImplementation orderServiceImplementation;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Validated OrderDto orderDtoRequest){
        try {
            OrderResponse orderDto = orderServiceImplementation.createOrder(orderDtoRequest);
            if(orderDto==null){
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.ok(orderDto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }



    @PutMapping("/finalized")
    public ResponseEntity<OrderDto> finalizedOrder(@RequestBody@Validated EmailRequest email){
        try{
            orderServiceImplementation.finalizedOrder(email.getEmail());
            return ResponseEntity.ok(null);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }

    }
    @GetMapping("/toList")
    public ResponseEntity<List<OrderResponse>> viewOrders(){
        try {
            return ResponseEntity.ok((orderServiceImplementation.toListOrders()));
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<OrderUpdateResponse> updateOrder(@RequestBody OrderUpdateDto orderUpdateDto){
        try{
            return ResponseEntity.ok(orderServiceImplementation.updateOrder(orderUpdateDto));

        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/toListOpen")
    public ResponseEntity<List<OrderResponse>> viewOrdersOpen(){
        try {
            return ResponseEntity.ok((orderServiceImplementation.toListOrdersOpen()));
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }


}
