package com.ventaproductos.orderitem.entity;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemDTOUpdate(

    @NotNull(message =  "required field") 
    Integer productId,
    @NotNull(message =  "required field") 
    Integer orderId,
    @NotNull(message =  "required field") 
    BigDecimal quantity,
    @NotNull(message =  "required field") 
    BigDecimal unitPrice

) {
    
}
