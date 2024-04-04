package com.ventaproductos.payment.entity;

import org.springframework.stereotype.Service;

import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.orderitem.repository.OrderItemRepository;

@Service
public class PaymentTotalItems {
    
    private OrderItemRepository repository;
    
    public PaymentTotalItems(OrderItemRepository repository) {
        this.repository = repository;
    }

    public double totalItems(Integer orderItemId) {
        return repository.findById(orderItemId).stream().mapToDouble(OrderItemEntity::getUnitPrice).sum();
    }
}
