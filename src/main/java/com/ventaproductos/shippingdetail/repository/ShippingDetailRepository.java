package com.ventaproductos.shippingdetail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

public interface ShippingDetailRepository extends JpaRepository<ShippingDetailEntity, Integer>{
    
    @Query("SELECT s FROM ShippingDetailEntity s WHERE s.order.id = :orderId")
    ShippingDetailEntity findByOrderId(@Param("orderId") Integer orderId);

    List<ShippingDetailEntity> findByConveyor(String conveyor);

    @Query("SELECT s FROM ShippingDetailEntity s WHERE s.order.status = :status")
    List<ShippingDetailEntity> findByOrderByStatus(@Param("status") String status);
    
}
