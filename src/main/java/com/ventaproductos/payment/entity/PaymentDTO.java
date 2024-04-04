package com.ventaproductos.payment.entity;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PaymentDTO(
    Integer id,
    double totalPayment,
    LocalDate paymentDate,
    Integer orderId,
    PaymentMethodEnum paymentMethod
) {
    
}
