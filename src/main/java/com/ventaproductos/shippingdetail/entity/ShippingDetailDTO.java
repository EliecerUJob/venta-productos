package com.ventaproductos.shippingdetail.entity;

import lombok.Builder;

@Builder
public record ShippingDetailDTO(

    Integer id,
    Integer orderId,
    String address,
    String conveyor,
    int guideNumber,
    Integer productId

) {
}
