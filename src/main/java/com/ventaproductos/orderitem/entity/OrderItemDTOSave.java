package com.ventaproductos.orderitem.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.product.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;

import lombok.Builder;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record OrderItemDTOSave(

    @NotNull(message = "required field")
    Integer productId,
    @NotNull(message = "required field")
    Integer orderId,
    @NotNull(message = "required field")
    Integer quantity,
    @NotNull(message = "required field")
    BigDecimal unitPrice,
    @JsonIgnore
    OrderEntity order,
    @JsonIgnore
    ProductEntity product

) {
    
}
