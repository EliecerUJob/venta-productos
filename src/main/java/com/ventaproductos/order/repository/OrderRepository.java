package com.ventaproductos.order.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.entity.OrderStatusEnum;

import java.util.List;
import com.ventaproductos.client.entity.ClientEntity;


public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
    
    List<OrderEntity> findByDateOrderBetween(LocalDate start, LocalDate end);
    List<OrderEntity> findByClientAndStatus(ClientEntity client, OrderStatusEnum status);

}
