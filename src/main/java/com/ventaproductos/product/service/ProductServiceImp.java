package com.ventaproductos.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductServiceInterface{

    private ProductRepository repository;

    public ProductServiceImp(ProductRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    @Override
    public ProductEntity create(ProductEntity product) {
        return repository.save(product);
    }

    @Override
    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ProductEntity> get(Integer id) {
        return repository.findById(id);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ProductEntity> update(Integer id, ProductEntity product) {
        return repository.findById(id).map( productDb -> {

            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setStock(product.getStock());

            return repository.save(productDb);
        });
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductEntity> getByNameContaining(String term) {
        return repository.findByNameContaining(term);
    }

    @Override
    public List<ProductEntity> getByStock(int stock) {
        return repository.findByStock(stock);
    }

    @Override
    public List<ProductEntity> getByPriceAndStock(BigDecimal price, int stock) {
        return repository.findByPriceAndStock(price, stock);
    }
    
}
