package com.ventaproductos.orderitem.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemDTOUpdate(

    @NotNull(message =  "required field") 
    Integer productId,
    @NotNull(message =  "required field") 
    Integer orderId,
    @NotNull(message =  "required field") 
    int quantity,
    @NotNull(message =  "required field") 
    double unitPrice

) {
    
}
