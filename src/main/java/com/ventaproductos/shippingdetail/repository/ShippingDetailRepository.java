package com.ventaproductos.shippingdetail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

public interface ShippingDetailRepository extends JpaRepository<ShippingDetailEntity, Integer>{
    
    List<ShippingDetailEntity> findByConveyor(String conveyor);
    List<ShippingDetailEntity> findByOrderStatus(@Param("status") String status);
    List<ShippingDetailEntity> findByOrderId(@Param("orderId") Integer orderId);
    
}
