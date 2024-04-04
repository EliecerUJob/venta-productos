package com.ventaproductos.order.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.entity.OrderStatusEnum;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
    
    List<OrderEntity> findByDateOrderBetween(LocalDate start, LocalDate end);
    List<OrderEntity> findByClientIdAndStatus(Integer client, OrderStatusEnum status);

    @Query(value = "SELECT * FROM orders p JOIN order_items oi ON p.id = oi.order_id WHERE p.client_id = :clientId", nativeQuery=true)
    List<Object[]> recuperateOrderWithItemsByCustomer(@Param("clientId") Integer clientId);

}
