package com.ventaproductos.product.entity;

import lombok.Builder;

import com.ventaproductos.orderitem.entity.OrderItemDTO;

@Builder
public record ProductDTO(

    Integer id,
    String name,
    int stock,
    double price,
    OrderItemDTO orderItem
    
) {
}
