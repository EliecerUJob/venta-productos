package com.ventaproductos.client.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ClientDTOSave (
    @NotNull(message = "required field")
    String name, 
    @NotNull(message = "required field")
    String email, 
    @NotNull(message = "required field")
    String address
){}
