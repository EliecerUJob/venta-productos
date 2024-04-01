package com.ventaproductos.product.entity;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductDTOSave(

    @NotNull(message = "required field")
    String name,
    @NotNull(message = "required field")
    BigDecimal  price,
    @NotNull(message = "required field")
    int stock

) {
    
}
