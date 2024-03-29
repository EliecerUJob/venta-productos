package com.ventaproductos.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ventaproductos.product.entity.ProductDTO;

public interface ProductServiceInterface {
    
    ProductDTO create(ProductDTO product);
    List<ProductDTO> getAll();
    Optional<ProductDTO> get(Integer id);
    Optional<ProductDTO> update(Integer id, ProductDTO product);
    void delete(Integer id);

    List<ProductDTO> getByNameContaining(String term);
    List<ProductDTO> getByStock(int stock);
    List<ProductDTO> getByPriceAndStock(BigDecimal price, int stock);

}
