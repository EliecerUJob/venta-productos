package com.ventaproductos.payment.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PaymentDTOUpdate(

    @NotNull(message = "required field")
    Integer orderId,
    @NotNull(message = "required field")
    LocalDate paymentDate,
    @NotNull(message = "required field")
    PaymentMethodEnum paymentMethod

) {
    
}
