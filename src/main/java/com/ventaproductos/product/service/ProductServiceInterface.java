package com.ventaproductos.product.service;

import java.util.List;

import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.entity.ProductDTOSave;

public interface ProductServiceInterface {
    
    ProductDTO create(ProductDTOSave product);
    List<ProductDTO> getAll();
    ProductDTO get(Integer id);
    ProductDTO update(Integer id, ProductDTOSave product);
    void delete(Integer id);

    List<ProductDTO> getByNameContaining(String term);
    List<ProductDTO> getByStock(int quantity);
    List<ProductDTO> getByPriceAndStock(double price, int stock);

}
