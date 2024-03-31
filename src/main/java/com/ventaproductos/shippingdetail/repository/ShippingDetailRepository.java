package com.ventaproductos.shippingdetail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

public interface ShippingDetailRepository extends JpaRepository<ShippingDetailEntity, Integer>{
    
    // ShippingDetailEntity findByOrderById(Integer id);
    List<ShippingDetailEntity> findByConveyor(String conveyor);
    // List<ShippingDetailEntity> findByOrderByStatus(OrderStatusEnum status);
    
}
