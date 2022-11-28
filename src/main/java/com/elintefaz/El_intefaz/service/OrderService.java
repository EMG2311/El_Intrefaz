package com.elintefaz.El_intefaz.service;

import com.elintefaz.El_intefaz.dto.OrderDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface OrderService {
        OrderDto createOrder(OrderDto orderDto);
}
