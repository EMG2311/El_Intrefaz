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
        if(order != null && order.getFinalized()==false){
            throw new OrderException();
        }
        else{
            Order order1=orderMapper.converToEntity(orderDto);
            order1.setHighDate(new Date());
            order1.setFinalized(false);
            orderRepository.save(order1);
        }
        return orderDto;
    }
}
