package com.elintefaz.El_intefaz.service;

import com.elintefaz.El_intefaz.Response.OrderResponse;
import com.elintefaz.El_intefaz.Response.OrderUpdateResponse;
import com.elintefaz.El_intefaz.dto.OrderDto;
import com.elintefaz.El_intefaz.dto.OrderUpdateDto;
import com.elintefaz.El_intefaz.model.Order;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public interface OrderService {
        OrderResponse createOrder(OrderDto orderDto);

        void finalizedOrder(String email);

        OrderUpdateResponse updateOrder(OrderUpdateDto orderDto);

        List<OrderResponse> toListOrders();

        List<OrderResponse> toListOrdersOpen();
}
