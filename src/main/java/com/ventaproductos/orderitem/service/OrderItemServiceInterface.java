package com.ventaproductos.orderitem.service;

import java.util.List;

import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.orderitem.entity.OrderItemDTOSave;
import com.ventaproductos.orderitem.entity.OrderItemDTOUpdate;

public interface OrderItemServiceInterface {
    
    OrderItemDTO create(OrderItemDTOSave oItem);
    OrderItemDTO get(Integer id);
    List<OrderItemDTO> getAll();
    OrderItemDTO update(Integer id, OrderItemDTOUpdate oItem);
    void delete(Integer id);

    List<OrderItemDTO> getOrderItemsdByProductId(Integer product);
    List<OrderItemDTO> getOrderItemsByOrderId(Integer id);
    Double sumTotalSalesByProduct(Integer productId);
    
}
