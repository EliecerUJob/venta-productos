package com.ventaproductos.product.repository;

import com.ventaproductos.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByNameContaining(String term);
    List<ProductEntity> findByStock(int stock);
    List<ProductEntity> findByPriceAndStock(BigDecimal price, int stock);

}
