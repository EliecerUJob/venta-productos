package com.ventaproductos.shippingdetail.entity;

import com.ventaproductos.order.entity.OrderDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDetailDTO {
    
    private Integer id;
    private String address;
    private String conveyor;
    private int guideNumber;

    private OrderDTO order;

}
