package com.ventaproductos.TestRepositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ventaproductos.AbstractIntegrationTest;
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.repository.ProductRepository;

public class TestProductRepository extends AbstractIntegrationTest{
    
    private final ProductRepository productRepository;
    
    @Autowired
    public TestProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressWarnings("null")
    void init(){

        ProductEntity product = ProductEntity.builder()
                        .name("Product 1")
                        .price(310.0)
                        .stock(5)
                        .build();
        
        productRepository.save(product);
        productRepository.flush();                

    }

    @BeforeEach
    void setUp(){
        productRepository.deleteAll();
    }

    @Test
    void givenProduct_whenFindByPriceLessThanAndStockLessThan_thenReturnProduct(){
        //Given
        init();
        //When
        List<ProductEntity> products = productRepository.findByPriceLessThanAndStockLessThan(320.0, 10);
        //Then
        assertEquals(1, products.size());
    }

    @Test
    void givenProduct_whenFindByPriceLessThanAndStockLessThan_thenReturnEmpty(){
        //Given
        init();
        //When
        List<ProductEntity> products = productRepository.findByPriceLessThanAndStockLessThan(5000.0, 5);
        //Then
        assertEquals(0, products.size());
    }

    @Test
    void givenProduct_whenFindByNameContaining_thenReturnProduct(){
        //Given
        init();
        //When
        List<ProductEntity> products = productRepository.findByNameContaining("Product 1");
        //Then
        assertEquals(1, products.size());
    }

    @Test
    void givenProduct_whenFindByNameContaining_thenReturnEmpty(){
        //Given
        init();
        //When
        List<ProductEntity> products = productRepository.findByNameContaining("Product 2");
        //Then
        assertEquals(0, products.size());
    }

    @Test
    void givenProduct_whenFindByStockGreaterThan_thenReturnProduct(){
        //Given
        init();
        //When
        List<ProductEntity> products = productRepository.findByStockGreaterThan(2);
        //Then
        assertEquals(1, products.size());
    }

    @Test
    void givenProduct_whenFindByStockGreaterThan_thenReturnEmpty(){
        //Given
        init();
        //When
        List<ProductEntity> products = productRepository.findByStockGreaterThan(10);
        //Then
        assertEquals(0, products.size());
    }

}
