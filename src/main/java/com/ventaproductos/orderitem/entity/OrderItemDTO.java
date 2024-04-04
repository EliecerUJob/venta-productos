package com.ventaproductos.orderitem.entity;

import lombok.Builder;

@Builder
public record OrderItemDTO(
    int quantity,
    double unitPrice,
    Integer id,
    Integer orderId,
    Integer productId
) {

}
