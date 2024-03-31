package com.ventaproductos.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    @Mapping(target = "orderItems", ignore = true)
    ProductDTO toDTO(ProductEntity entity);

    @Mapping(target = "orderItems", ignore = true)
    ProductEntity toEntity(ProductDTO dto);

    List<ProductDTO> toProductDTOList(List<ProductEntity> productEntityList);
    List<ProductEntity> toProductEntityList(List<ProductDTO> productDTOList);
    
}
