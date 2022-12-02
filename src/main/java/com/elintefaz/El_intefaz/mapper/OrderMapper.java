package com.elintefaz.El_intefaz.mapper;

import com.elintefaz.El_intefaz.dto.OrderDto;
import com.elintefaz.El_intefaz.dto.ProductsDto;
import com.elintefaz.El_intefaz.model.Order;
import com.elintefaz.El_intefaz.model.Products;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper mapper;
    public Order converToEntity(OrderDto orderDto){
        return mapper.map(orderDto,Order.class);
    }
    public OrderDto converToDto(Order order){
        return mapper.map(order,OrderDto.class);
    }

}
