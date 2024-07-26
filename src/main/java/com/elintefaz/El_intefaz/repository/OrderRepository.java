package com.elintefaz.El_intefaz.repository;

import com.elintefaz.El_intefaz.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Optional<Order> findByEmail(String email);

    @Query(value = "SELECT * FROM elintefaz.order o WHERE COALESCE(o.end_date, NOW() + INTERVAL '100' DAY) > NOW()", nativeQuery = true)
    List<Order> findAllVigente();

    @Query(value = "SELECT * FROM elintefaz.order o WHERE o.email = :email AND COALESCE(o.end_date, NOW() + INTERVAL '100' DAY) > NOW()", nativeQuery = true)
    Optional<Order> findByEmailVigente(@Param("email") String email);

}