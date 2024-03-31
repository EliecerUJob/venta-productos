package com.ventaproductos.product.service;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.product.entity.ProductDTO;
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
    public ProductDTO create(ProductDTO product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        return productMapper.toDTO(repository.save(productEntity));
    }

    @Override
    public List<ProductDTO> getAll() {
        return productMapper.toProductDTOList(repository.findAll());
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ProductDTO> get(Integer id) {
        ProductEntity productEntity = repository.findById(id).get();
        return Optional.of(productMapper.toDTO(productEntity));
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ProductDTO> update(Integer id, ProductDTO product) {
        return repository.findById(id).map( productDb -> {

            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setStock(product.getStock());

            return productMapper.toDTO(repository.save(productDb));
        });
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getByNameContaining(String term) {
        return productMapper.toProductDTOList(repository.findByNameContaining(term));
    }

    @Override
    public List<ProductDTO> getByStock() {
        // List<ProductDTO> productInStockDtoList = new ArrayList<>();
        // repository.findAll().stream().forEach( product -> {
        //     if (product.getStock() >= 0) {
        //         productInStockDtoList.add(productMapper.toDTO(product));
        //     }
        // });

        // return productInStockDtoList;

        return productMapper.toProductDTOList(repository.searchByStock());
    }

    @Override
    public List<ProductDTO> getByPriceAndStock(BigDecimal price, int stock) {
        return productMapper.toProductDTOList(repository.findByPriceAndStock(price, stock));
    }
    
}
