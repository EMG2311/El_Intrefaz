package com.elintefaz.El_intefaz.service.implementation;

import com.elintefaz.El_intefaz.dto.OrderDto;
import com.elintefaz.El_intefaz.exception.OrderException;
import com.elintefaz.El_intefaz.mapper.OrderMapper;
import com.elintefaz.El_intefaz.model.Order;
import com.elintefaz.El_intefaz.repository.OrderRepository;
import com.elintefaz.El_intefaz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order=orderRepository.findByEmail(orderDto.getEmail());
        if(order != null){
            throw new OrderException();
        }
        else{
            Order order1=orderMapper.converToEntity(orderDto);
            order1.setStarDate(new Date());
            orderRepository.save(order1);
        }
        return orderDto;
    }



    @Override
    public OrderDto finalizedOrder(Integer idOrder) {
        Order order=orderRepository.findById(idOrder).get();
        order.setEndDate(new Date());
        orderRepository.save(order);
        return orderMapper.converToDto(order);
    }


}
