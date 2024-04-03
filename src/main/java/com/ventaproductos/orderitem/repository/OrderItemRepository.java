package com.ventaproductos.orderitem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ventaproductos.orderitem.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer>{
    
    List<OrderItemEntity> findByOrderId(Integer id);
    List<OrderItemEntity> findByProductName(String name);
    List<OrderItemEntity> findByProductId(Integer id);
    
    @Query("SELECT SUM(oi.quantity * oi.unitPrice) FROM OrderItemEntity oi WHERE oi.product.id = :productId")
    Double sumTotalSalesByProduct(@Param("productId") Integer productId);

}
