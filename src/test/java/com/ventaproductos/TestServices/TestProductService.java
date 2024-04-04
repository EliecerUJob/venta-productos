package com.ventaproductos.TestServices;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ventaproductos.Exceptions.ProductNotFoundException;
import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.entity.ProductDTOSave;
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.mapper.ProductMapper;
import com.ventaproductos.product.repository.ProductRepository;
import com.ventaproductos.product.service.ProductServiceImp;

@ExtendWith(MockitoExtension.class)
public class TestProductService {
    
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductServiceImp productService;

    private ProductEntity productEntity;
    private List<ProductEntity> productEntityList;
    private List<ProductDTO> productDTOList;

    @BeforeEach
    void setup(){
        productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("Producto");
        productEntity.setPrice(100.00);
        productEntity.setStock(100);
        
        productEntityList = List.of(productEntity);

    }

    @Test
    void givenProduct_whenCreateProduct_thenReturnProductDTO() {
        // Given
        var productSave = ProductDTOSave.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .build();

                given(productMapper.toEntity(productSave)).willReturn(productEntity);
                given(productRepository.save(productEntity)).willReturn(productEntity);
                given(productMapper.toDTO(productEntity)).willReturn(ProductDTO.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .stock(productEntity.getStock())
                        .build());
        //When
        ProductDTO productDTO = productService.create(productSave);
        //Then
        assertThat(productDTO).isNotNull();
        assertThat(productDTO.name()).isEqualTo(productEntity.getName());
        assertThat(productDTO.price()).isEqualTo(productEntity.getPrice());
        assertThat(productDTO.stock()).isEqualTo(productEntity.getStock());
    }

    @Test
    void givenProduct_whenGetAllProducts_thenReturnProductDTOList() {
        // Given
        given(productRepository.findAll()).willReturn(productEntityList);
        given(productMapper.toDTO(productEntity)).willReturn(ProductDTO.builder()
         .id(productEntity.getId())
         .name(productEntity.getName())
         .price(productEntity.getPrice())
         .stock(productEntity.getStock())
         .build()
        );

        // When
        productDTOList = productService.getAll();

        //Then
        assertThat(productDTOList).hasSize(1);
        assertThat(productDTOList.get(0).id()).isEqualTo(productEntity.getId());
        assertThat(productDTOList.get(0).name()).isEqualTo(productEntity.getName());
        assertThat(productDTOList.get(0).price()).isEqualTo(productEntity.getPrice());
        assertThat(productDTOList.get(0).stock()).isEqualTo(productEntity.getStock());
    }

    @Test
    void givenProduct_whenGetProductById_thenReturnProductDTO() {
        // Given
        given(productRepository.findById(1)).willReturn(Optional.of(productEntity));
        given(productMapper.toDTO(productEntity)).willReturn(ProductDTO.builder()
         .id(productEntity.getId())
         .name(productEntity.getName())
         .price(productEntity.getPrice())
         .stock(productEntity.getStock())
         .build()
        );
        //When
        ProductDTO productDTO = productService.get(1);
        //Then
        assertThat(productDTO).isNotNull();
        assertThat(productDTO.id()).isEqualTo(productEntity.getId());
        assertThat(productDTO.name()).isEqualTo(productEntity.getName());
        assertThat(productDTO.price()).isEqualTo(productEntity.getPrice());
        assertThat(productDTO.stock()).isEqualTo(productEntity.getStock());
    }

    @Test
    void givenProduct_whenGetProductById_thenReturnEmpty(){
        // Given
        Integer id = 1;
        given(productRepository.findById(id)).willReturn(Optional.empty());
        //When

        //Then
        assertThrows(ProductNotFoundException.class, ()->productService.get(id), "Product not found");
    }

    @Test
    void givenProduct_whenUpdateProduct_thenUpdateProduct() {
        // Given
        var productSaveDTO = ProductDTOSave.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .build();
        var productDto = ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .build();

        given(productRepository.findById(productEntity.getId())).willReturn(Optional.of(productEntity));
        given(productRepository.save(any(ProductEntity.class))).willReturn(productEntity);
        given(productMapper.toDTO(productEntity)).willReturn(productDto);


        // When
        var productUpdate = productService.update(productEntity.getId(), productSaveDTO);

        // Then
        assertThat(productUpdate).isNotNull();
        assertEquals(productUpdate.id(), productEntity.getId());
        assertEquals(productUpdate.name(), productEntity.getName());
        assertEquals(productUpdate.price(), productEntity.getPrice());
        assertEquals(productUpdate.stock(), productEntity.getStock());
    }

    @Test
    void givenProductId_whenUpdateProduct_thenProductNotUpdate() {
        // Given
        Integer id = 1;
        given(productRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(ProductNotFoundException.class, () -> productService.update(id, ProductDTOSave.builder().build()), "Product not found");
    }

    @Test
    void givenProductId_whenDeleteProduct_thenProductNotDelete() {
        // Given
        Integer id = 1;
        given(productRepository.findById(id)).willReturn(Optional.empty());
        // When
        // Then
        assertThrows(ProductNotFoundException.class, () -> productService.delete(id), "Product not found");
    }

    @Test
    void givenProductId_whenDeleteProduct_thenProductDeleted() {
        Integer id = 1;
        given(productRepository.findById(id)).willReturn(Optional.of(productEntity));

        willDoNothing().given(productRepository).delete(productEntity);
        productService.delete(id);

        verify(productRepository, times(1)).delete(productEntity);
    }

    @Test
    void givenProduct_whenGetProductByNameContaining_thenReturnProductDTOList() {
        // Given
        given(productRepository.findByNameContaining(productEntity.getName())).willReturn(productEntityList);
        given(productMapper.toDTO(productEntity)).willReturn(ProductDTO.builder()
         .id(productEntity.getId())
         .name(productEntity.getName())
         .price(productEntity.getPrice())
         .stock(productEntity.getStock())
         .build()
        );
        //When
        productDTOList = productService.getByNameContaining(productEntity.getName());
        //Then
        assertThat(productDTOList).isNotEmpty();
        assertThat(productDTOList).hasSize(1);
        assertThat(productDTOList.get(0).id()).isEqualTo(productEntity.getId());
        assertThat(productDTOList.get(0).name()).isEqualTo(productEntity.getName());
        assertThat(productDTOList.get(0).price()).isEqualTo(productEntity.getPrice());
        assertThat(productDTOList.get(0).stock()).isEqualTo(productEntity.getStock());
    }

    @Test
    void givenQuantity_whenGetProductByStock_thenReturnProductDTOList() {
        // Given
        given(productRepository.findByStockGreaterThan(productEntity.getStock())).willReturn(productEntityList);
        given(productMapper.toDTO(productEntity)).willReturn(ProductDTO.builder()
         .id(productEntity.getId())
         .name(productEntity.getName())
         .price(productEntity.getPrice())
         .stock(productEntity.getStock())
         .build()
        );
        //When
        productDTOList = productService.getByStock(productEntity.getStock());
        //Then
        assertThat(productDTOList).isNotEmpty();
        assertThat(productDTOList).hasSize(1);
        assertThat(productDTOList.get(0).id()).isEqualTo(productEntity.getId());
        assertThat(productDTOList.get(0).name()).isEqualTo(productEntity.getName());
        assertThat(productDTOList.get(0).price()).isEqualTo(productEntity.getPrice());
    }

    @Test
    void givenProduct_whenGetProductByPriceAndStock_thenReturnProductDTOList() {
        // Given
        given(productRepository.findByPriceLessThanAndStockLessThan(productEntity.getPrice(), productEntity.getStock())).willReturn(productEntityList);
        given(productMapper.toDTO(productEntity)).willReturn(ProductDTO.builder()
         .id(productEntity.getId())
         .name(productEntity.getName())
         .price(productEntity.getPrice())
         .stock(productEntity.getStock())
         .build()
        );
        //When
        productDTOList = productService.getByPriceAndStock(productEntity.getPrice(), productEntity.getStock());
        //Then
        assertThat(productDTOList).isNotEmpty();
        assertThat(productDTOList).hasSize(1);
        assertThat(productDTOList.get(0).id()).isEqualTo(productEntity.getId());
        assertThat(productDTOList.get(0).name()).isEqualTo(productEntity.getName());
    }

}
