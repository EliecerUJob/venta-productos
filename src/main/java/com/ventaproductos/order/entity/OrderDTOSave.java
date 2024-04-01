package com.ventaproductos.order.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderDTOSave(
    @NotNull(message = "required field")
    LocalDate dateOrder,
    @NotNull(message = "required field")
    OrderStatusEnum status,
    @NotNull(message = "required field")
    Integer clientId
) {
    
}
