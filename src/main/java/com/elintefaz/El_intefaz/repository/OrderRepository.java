package com.elintefaz.El_intefaz.repository;

import com.elintefaz.El_intefaz.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {


}
