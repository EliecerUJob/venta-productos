package com.ventaproductos.orderitem.service;

import java.util.List;
import java.util.Optional;

import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.product.entity.ProductDTO;

public interface OrderItemServiceInterface {
    
    OrderItemDTO create(OrderItemDTO oItem);
    Optional<OrderItemDTO> get(Integer id);
    List<OrderItemDTO> getAll();
    Optional<OrderItemDTO> update(Integer id, OrderItemDTO oItem);
    void delete(Integer id);

    // OrderItemDTO getByOrderById(OrderEntity order, Integer id);
    List<OrderItemDTO> findByProduct(ProductDTO product);
    
}
