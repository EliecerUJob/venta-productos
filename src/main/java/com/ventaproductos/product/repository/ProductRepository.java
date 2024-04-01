package com.ventaproductos.product.repository;

import com.ventaproductos.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByNameContaining(String term);
    List<ProductEntity> findByPriceLessThanAndStockLessThan(BigDecimal price, int stock);
    List<ProductEntity> findByStockGreaterThan(int quantity);

}
