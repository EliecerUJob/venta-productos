package com.ventaproductos.product.entity;

import lombok.Builder;
import java.math.*;

import com.ventaproductos.orderitem.entity.OrderItemDTO;

@Builder
public record ProductDTO(

    Integer id,
    String name,
    int stock,
    BigDecimal price,
    OrderItemDTO orderItem
    
) {
}
