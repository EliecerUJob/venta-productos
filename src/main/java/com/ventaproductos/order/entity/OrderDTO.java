package com.ventaproductos.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;

@Getter
@Setter
public class OrderDTO {
    
    private Integer id;
    private LocalDate dateOrder;
    private OrderStatusEnum status;

    private ClientDTO client;

    private List<OrderItemDTO> orderItems;
    private PaymentDTO payment;
    private ShippingDetailDTO shippingDetail;

}
