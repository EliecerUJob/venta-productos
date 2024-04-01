package com.ventaproductos.shippingdetail.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ShippingDetailDTOUpdate(

    @NotNull(message = "required field")
    Integer orderId,
    @NotNull(message = "required field")
    Integer productId,
    @NotNull(message = "required field")
    String address,
    @NotNull(message = "required field")
    String conveyor,
    @NotNull(message = "required field")
    int guideNumber

) {

}
