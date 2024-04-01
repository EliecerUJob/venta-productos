package com.ventaproductos.order.entity;

import lombok.Builder;

import java.time.LocalDate;
import java.util.*;

import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;

@Builder
public record OrderDTO(
    Integer id,
    LocalDate dateOrder,
    OrderStatusEnum status,
    Integer clientId,
    List<OrderItemDTO> orderItems,
    PaymentDTO payment,
    ShippingDetailDTO shippingDetail
) {
    
    public List<OrderItemDTO> orderItems(){
        return Collections.unmodifiableList(orderItems);
    }

}
