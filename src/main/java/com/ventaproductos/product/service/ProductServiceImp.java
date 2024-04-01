package com.ventaproductos.product.service;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.entity.ProductDTOSave;
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.mapper.ProductMapper;
import com.ventaproductos.product.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductServiceInterface{

    private ProductRepository repository;
    private ProductMapper productMapper;

    public ProductServiceImp(ProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    @SuppressWarnings("null")
    @Override
    public ProductDTO create(ProductDTOSave product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        return productMapper.toDTO(repository.save(productEntity));
    }

    @Override
    public List<ProductDTO> getAll() {
        var productList = repository.findAll();
        return productList.stream().map( productMapper::toDTO ).toList();
    }

    @SuppressWarnings("null")
    @Override
    public ProductDTO get(Integer id) {
        ProductEntity productEntity = repository.findById(id).get();
        return productMapper.toDTO(productEntity);
    }

    @SuppressWarnings("null")
    @Override
    public ProductDTO update(Integer id, ProductDTOSave product) {
        return repository.findById(id).map( productDb -> {

            productDb.setName(product.name());
            productDb.setPrice(product.price());
            productDb.setStock(product.stock());

            return productMapper.toDTO(repository.save(productDb));
        }).orElseThrow();
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getByNameContaining(String term) {
        var productList = repository.findByNameContaining(term);
        return productList.stream().map( productMapper::toDTO ).toList();
    }

    @Override
    public List<ProductDTO> getByStock(int quantity) {
        var productList = repository.findByStockGreaterThan(quantity);
        return productList.stream().map( productMapper::toDTO ).toList();
    }

    @Override
    public List<ProductDTO> getByPriceAndStock(BigDecimal price, int stock) {
        var productList = repository.findByPriceLessThanAndStockLessThan(price, stock);
        return productList.stream().map( productMapper::toDTO ).toList();
    }
    
}
