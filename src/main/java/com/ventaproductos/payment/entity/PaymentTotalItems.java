package com.ventaproductos.payment.entity;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.orderitem.repository.OrderItemRepository;

@Service
public class PaymentTotalItems {
    
    private OrderItemRepository repository;
    
    public PaymentTotalItems(OrderItemRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    public BigDecimal totalItems(Integer orderItemId) {
        return repository.findById(orderItemId).stream().map(OrderItemEntity::getUnitPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
