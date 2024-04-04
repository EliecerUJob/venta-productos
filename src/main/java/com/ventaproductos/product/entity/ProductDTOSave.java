package com.ventaproductos.product.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductDTOSave(

    @NotNull(message = "required field")
    String name,
    @NotNull(message = "required field")
    double  price,
    @NotNull(message = "required field")
    int stock

) {
    
}
