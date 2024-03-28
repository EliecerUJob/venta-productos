package com.ventaproductos.orderitem.service;

import java.util.List;
import java.util.Optional;

import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.product.entity.ProductEntity;

public interface OrderItemServiceInterface {
    
    OrderItemEntity create(OrderItemEntity oItem);
    Optional<OrderItemEntity> get(Integer id);
    List<OrderItemEntity> getAll();
    Optional<OrderItemEntity> update(Integer id, OrderItemEntity oItem);
    void delete(Integer id);

    // OrderItemEntity getByOrderById(OrderEntity order, Integer id);
    List<OrderItemEntity> findByProduct(ProductEntity product);
    
}
