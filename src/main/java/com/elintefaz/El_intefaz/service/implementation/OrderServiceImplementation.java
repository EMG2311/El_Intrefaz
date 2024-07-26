package com.elintefaz.El_intefaz.service.implementation;

import com.elintefaz.El_intefaz.Response.OrderResponse;
import com.elintefaz.El_intefaz.Response.OrderUpdateResponse;
import com.elintefaz.El_intefaz.dto.OrderDto;
import com.elintefaz.El_intefaz.dto.OrderUpdateDto;
import com.elintefaz.El_intefaz.dto.ProductOrderDto;
import com.elintefaz.El_intefaz.exception.OrderException;
import com.elintefaz.El_intefaz.mapper.OrderMapper;
import com.elintefaz.El_intefaz.model.Order;
import com.elintefaz.El_intefaz.model.OrderItem;
import com.elintefaz.El_intefaz.model.Products;
import com.elintefaz.El_intefaz.repository.OrderItemRepository;
import com.elintefaz.El_intefaz.repository.OrderRepository;
import com.elintefaz.El_intefaz.repository.ProductsRepository;
import com.elintefaz.El_intefaz.service.OrderService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    private final static Logger LOGGER =  LoggerFactory.getLogger(OrderServiceImplementation.class);


    @Override
    @Transactional
    public OrderResponse createOrder(OrderDto orderDto) {
        try {
            Order orderExist = orderRepository.findByEmailVigente(orderDto.getEmail()).orElse(null);
            if (orderExist != null && orderExist.getEndDate() == null) {
                throw new OrderException("Ya existe una orden activa para el mail " + orderDto.getEmail());
            }

            Order order = Order.builder()
                    .nCel(orderDto.getNCel())
                    .email(orderDto.getEmail())
                    .address(orderDto.getAddress())
                    .starDate(new Date())
                    .build();

            Double total=0D;

            for(ProductOrderDto p: orderDto.getProducts()){
                LOGGER.info("----------Buscando si existen los productos para crear la orden----------");
                Products prod =productsRepository.findByName(p.getName())
                        .orElseThrow(()->{throw new EntityNotFoundException("No existe el producto " + p.getName());});
                total=total+prod.getPrice()*p.getQuantity();
            }


            Order finalOrder = order;
            List<OrderItem> orderItems = orderDto.getProducts().stream().map(productOrderDto -> {
                Products product = productsRepository.findByName(productOrderDto.getName())
                        .orElseThrow(() -> new EntityNotFoundException("No existe el producto " + productOrderDto.getName()));
                if (product.getStock()<productOrderDto.getQuantity()){
                    throw new EntityNotFoundException("El producto " +product.getName() + " no posee stock suficiente");
                }
                product.setStock(product.getStock()-productOrderDto.getQuantity());
                LOGGER.info("----------Guardando nuevo stock del producto----------");
                productsRepository.save(product);
                return OrderItem.builder()
                        .order(finalOrder)
                        .product(product)
                        .quantity(productOrderDto.getQuantity())
                        .build();
            }).collect(Collectors.toList());

            order.setOrderItems(orderItems);
            order.setTotal(total);
            LOGGER.info("----------CREANDO ORDEN----------");
            order = orderRepository.save(order);

            return OrderResponse.builder()
                    .id(order.getIdOrder())
                    .email(order.getEmail())
                    .address(order.getAddress())
                    .nCel(order.getNCel())
                    .productos(orderDto.getProducts())
                    .total(total)
                    .build();

        } catch (EntityNotFoundException n) {
            LOGGER.error(n.getMessage() + " intente de nuevo sin este producto");
            throw n;
        } catch (OrderException o) {
            LOGGER.error(o.getMessage());
            throw o;
        } catch (Exception e) {
            LOGGER.error("Error inesperado al crear orden " + e.getMessage());
            throw new RuntimeException("Error inesperado "+e);
        }
    }



    @Override
    public void finalizedOrder(String email) {
        try {
            Order order = orderRepository.findByEmailVigente(email).orElse(null);
            if (order==null){
                throw new EntityNotFoundException("No existe ninguna orden activa para el mail "+email);
            }
            order.setEndDate(new Date());
            LOGGER.info("----------Finalizano orden de " + email);
            orderRepository.save(order);
        }catch (EntityNotFoundException n){
            LOGGER.error(n.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error inesperado al finalizar orden "+e.getMessage());
        }
    }

    @Override
    @Transactional
    public OrderUpdateResponse updateOrder(OrderUpdateDto orderDto) {
        try {
            LOGGER.info("----------Buscando si existe una orden activa para ese mail----------");
            Order order = orderRepository.findByEmailVigente(orderDto.getEmail())
                    .orElseThrow(() ->
                    {
                        throw new EntityNotFoundException("No existe una orden vigente para el mail " + orderDto.getEmail());
                    });
            //orderRepository.delete(order);
            order.setAddress(orderDto.getAddress() == "" ? order.getAddress() : orderDto.getAddress());
            order.setEmail(orderDto.getNewEmail() == "" ? order.getEmail() : orderDto.getNewEmail());
            order.setNCel(orderDto.getNCel() == "" ? order.getNCel() : orderDto.getNCel());
            //Busco y modifico los OrderItems
            if (!orderDto.getProducts().isEmpty()) {
                Double total=0D;
                for(OrderItem o: order.getOrderItems()){
                    Products product=productsRepository.findByName(o.getProduct().getName())
                            .orElseThrow(()->{throw new EntityNotFoundException("No existe el producto ingresado "+o.getProduct().getName());});
                    product.setStock(product.getStock()+o.getQuantity());
                    total=total+product.getPrice()*o.getQuantity();
                    productsRepository.save(product);
                    LOGGER.info("---------Devolviendo al stock el producto "+o.getProduct().getName()+" ---------");
                }
                orderItemRepository.deleteByOrder(order);
                //LOGGER.info("Se elimino los items de la orden");
                Order finalOrder = order;
                List<OrderItem> orderItems = orderDto.getProducts().stream().map(productOrderDto -> {
                    Products product = productsRepository.findByName(productOrderDto.getName())
                            .orElseThrow(() -> new EntityNotFoundException("No existe el producto " + productOrderDto.getName()));
                    if (product.getStock() < productOrderDto.getQuantity()) {
                        throw new EntityNotFoundException("El producto " + product.getName() + " no posee stock suficiente");
                    }
                    product.setStock(product.getStock() - productOrderDto.getQuantity());
                    productsRepository.save(product);
                    LOGGER.info("---------Guardando item---------");
                    return OrderItem.builder()
                            .order(finalOrder)
                            .product(product)
                            .quantity(productOrderDto.getQuantity())
                            .build();
                }).collect(Collectors.toList());
                order.setOrderItems(orderItems);
                order.setTotal(total);
            }

            LOGGER.info("---------Guardando orden actualizada---------");
            order = orderRepository.save(order);

            return OrderUpdateResponse.builder()
                    .id(order.getIdOrder())
                    .email(orderDto.getEmail())
                    .newEmail(orderDto.getNewEmail())
                    .address(orderDto.getAddress())
                    .nCel(orderDto.getNCel())
                    .total(order.getTotal())
                    .products(orderDto.getProducts())
                    .build();
        }catch (EntityNotFoundException n){
            LOGGER.error("----------Error al actualizar "+n.getMessage());
            throw n;
        }catch (Exception e){
            LOGGER.error("Error inesperado" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<OrderResponse> toListOrders(){
        try {
            List<OrderResponse> orderDtos = new ArrayList<>();
            LOGGER.info("----------Buscando todas las ordenes----------");
            for (Order o : orderRepository.findAll()) {
                orderDtos.add(OrderResponse.builder()
                        .id(o.getIdOrder())
                        .email(o.getEmail())
                        .nCel(o.getNCel())
                        .address(o.getAddress())
                        .total(o.getTotal())
                        .productos(o.getOrderItems().stream().map((oi) ->
                                ProductOrderDto
                                        .builder()
                                        .name(oi.getProduct().getName())
                                        .quantity(oi.getQuantity())
                                        .build()
                        ).collect(Collectors.toList())).build());
            }

            return orderDtos;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<OrderResponse> toListOrdersOpen() {
        try {
            List<OrderResponse> orderDtos = new ArrayList<>();
            LOGGER.info("----------Buscando todas las ordenes abiertas----------");
            for (Order o : orderRepository.findAllVigente()) {
                orderDtos.add(OrderResponse.builder()
                        .id(o.getIdOrder())
                        .email(o.getEmail())
                        .nCel(o.getNCel())
                        .address(o.getAddress())
                        .total(o.getTotal())
                        .productos(o.getOrderItems().stream().map((oi) ->
                                ProductOrderDto
                                        .builder()
                                        .name(oi.getProduct().getName())
                                        .quantity(oi.getQuantity())
                                        .build()
                        ).collect(Collectors.toList())).build());
            }

            return orderDtos;
        }catch (Exception e){
            return null;
        }
    }


}
