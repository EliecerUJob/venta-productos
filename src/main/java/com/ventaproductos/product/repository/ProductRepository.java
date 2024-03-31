package com.ventaproductos.product.repository;

import com.ventaproductos.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByNameContaining(String term);

    @Query("SELECT p FROM ProductEntity p WHERE p.stock > 0")
    List<ProductEntity> searchByStock();

    List<ProductEntity> findByPriceAndStock(BigDecimal price, int stock);

}
