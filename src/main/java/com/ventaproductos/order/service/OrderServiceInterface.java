package com.ventaproductos.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderDTOSave;
import com.ventaproductos.order.entity.OrderStatusEnum;

public interface OrderServiceInterface {
    
    Optional<OrderDTO> get(Integer id);
    List<OrderDTO> getAll();
    void delete(Integer id);
    OrderDTO update(Integer id, OrderDTOSave order);
    OrderDTO create(OrderDTOSave order);

    List<OrderDTO> getByDateOrderBetween(LocalDate start, LocalDate end);
    List<OrderDTO> getOrderByClientIdAndStatus(Integer clientId, OrderStatusEnum status);
    // List<OrderDTORecuperate> getOrdersByRecuperateOrderWithItemsByClient(Integer clientId);

}
