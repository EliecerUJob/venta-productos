package com.ventaproductos.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.order.entity.OrderDTO;

public interface OrderServiceInterface {
    
    Optional<OrderDTO> get(Integer id);
    List<OrderDTO> getAll();
    void delete(Integer id);
    Optional<OrderDTO> update(Integer id, OrderDTO order);
    OrderDTO create(OrderDTO order);

    List<OrderDTO> getByDateOrderBetween(LocalDate start, LocalDate end);
    List<OrderDTO> getByClientAndStatus(ClientDTO client, String status);

}
