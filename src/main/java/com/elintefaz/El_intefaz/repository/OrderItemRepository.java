package com.elintefaz.El_intefaz.repository;

import com.elintefaz.El_intefaz.model.Order;
import com.elintefaz.El_intefaz.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    @Query("DELETE FROM OrderItem oi WHERE oi.order = :order")
    @Modifying
    @Transactional
    void deleteByOrder(Order order);
}
