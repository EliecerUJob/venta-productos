package com.ventaproductos.orderitem.entity;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemDTO(
    BigDecimal quantity,
    BigDecimal unitPrice,
    Integer id,
    Integer orderId,
    Integer productId
) {

}
