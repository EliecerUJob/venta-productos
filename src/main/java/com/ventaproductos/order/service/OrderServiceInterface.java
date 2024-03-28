package com.ventaproductos.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.order.entity.OrderEntity;

public interface OrderServiceInterface {
    
    Optional<OrderEntity> get(Integer id);
    List<OrderEntity> getAll();
    void delete(Integer id);
    Optional<OrderEntity> update(Integer id, OrderEntity order);
    OrderEntity create(OrderEntity order);

    List<OrderEntity> getByDateOrderBetween(LocalDate start, LocalDate end);
    List<OrderEntity> getByClientAndStatus(ClientEntity client, String status);

}
