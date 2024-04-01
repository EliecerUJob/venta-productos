package com.ventaproductos.order.entity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.ventaproductos.orderitem.entity.OrderItemDTOUpdate;

import lombok.Builder;

@Builder
public record OrderDTORecuperate(
    Integer id,
    LocalDate dateOrder,
    OrderStatusEnum status,
    Integer clientId,
    List<OrderItemDTOUpdate> orderItems
) {
    public List<OrderItemDTOUpdate> orderItems(){
        return Collections.unmodifiableList(orderItems);
    }
}
