package com.ventaproductos.payment.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ventaproductos.order.entity.OrderEntity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PaymentDTOSave(

    @NotNull(message = "required field")
    Integer orderId,
    @JsonIgnore
    OrderEntity order,
    @NotNull(message = "required field")
    LocalDate paymentDate,
    @NotNull(message = "required field")
    PaymentMethodEnum paymentMethod

) {
    
}
