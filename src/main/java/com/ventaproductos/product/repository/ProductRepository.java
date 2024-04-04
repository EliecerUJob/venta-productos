package com.ventaproductos.product.repository;

import com.ventaproductos.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByPriceLessThanAndStockLessThan(double price, int stock);
    List<ProductEntity> findByNameContaining(String term);
    List<ProductEntity> findByStockGreaterThan(int quantity);

}
