package com.ventaproductos.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ventaproductos.product.entity.ProductEntity;

public interface ProductServiceInterface {
    
    ProductEntity create(ProductEntity product);
    List<ProductEntity> getAll();
    Optional<ProductEntity> get(Integer id);
    Optional<ProductEntity> update(Integer id, ProductEntity product);
    void delete(Integer id);

    List<ProductEntity> getByNameContaining(String term);
    List<ProductEntity> getByStock(int stock);
    List<ProductEntity> getByPriceAndStock(BigDecimal price, int stock);

}
