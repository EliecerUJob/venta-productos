package com.ventaproductos.shippingdetail.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.product.entity.ProductEntity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ShippingDetailDTOSave(

    @NotNull(message = "required field")
    Integer productId,
    @NotNull(message = "required field")
    Integer orderId,
    @NotNull(message = "required field")
    String address,
    @NotNull(message = "required field")
    String conveyor,
    @NotNull(message = "required field")
    int guideNumber,
    @JsonIgnore
    OrderEntity order,
    @JsonIgnore
    ProductEntity product

) {
    


}
