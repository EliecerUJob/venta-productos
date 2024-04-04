package com.ventaproductos.product.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.Exceptions.ProductNotFoundException;
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

    @Override
    public ProductDTO get(Integer id) {
        ProductEntity productEntity = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.toDTO(productEntity);
    }

    @Override
    public ProductDTO update(Integer id, ProductDTOSave product) {
        return repository.findById(id).map( productDb -> {

            productDb.setName(product.name());
            productDb.setPrice(product.price());
            productDb.setStock(product.stock());

            return productMapper.toDTO(repository.save(productDb));
        }).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void delete(Integer id) {
        ProductEntity productEntity = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        repository.delete(productEntity);
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
    public List<ProductDTO> getByPriceAndStock(double price, int stock) {
        var productList = repository.findByPriceLessThanAndStockLessThan(price, stock);
        return productList.stream().map( productMapper::toDTO ).toList();
    }
    
}
