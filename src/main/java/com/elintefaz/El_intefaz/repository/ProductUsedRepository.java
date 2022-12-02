package com.elintefaz.El_intefaz.repository;

import com.elintefaz.El_intefaz.model.Order;
import com.elintefaz.El_intefaz.model.ProductUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUsedRepository extends JpaRepository<ProductUsed,Integer> {
    List<ProductUsed> findBynOrder(Order nOrder);


}
