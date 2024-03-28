package com.ventaproductos.orderitem.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.product.entity.ProductEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer>{
    
    // OrderItemEntity findByOrderById( OrderEntity order, Integer id);
    List<OrderItemEntity> findByProduct(ProductEntity product);

    @Query("SELECT SUM(oi.quantity * oi.unitPrice) FROM OrderItemEntity oi WHERE oi.product = :product")
    BigDecimal sumTotalSalesByProduct(@Param("product") ProductEntity product);

}
