package com.ventaproductos.shippingdetail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventaproductos.order.entity.OrderStatusEnum;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

public interface ShippingDetailRepository extends JpaRepository<ShippingDetailEntity, Integer>{
    
    List<ShippingDetailEntity> findByConveyor(String conveyor);
    List<ShippingDetailEntity> findByOrderStatus(OrderStatusEnum status);
    List<ShippingDetailEntity> findByOrderId(Integer orderId);
    
}
