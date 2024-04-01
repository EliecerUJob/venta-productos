package com.ventaproductos.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PaymentDTO(
    Long id,
    BigDecimal totalPayment,
    LocalDate paymenDate,
    Integer orderId,
    PaymentMethodEnum paymentMethod
) {
    
}
