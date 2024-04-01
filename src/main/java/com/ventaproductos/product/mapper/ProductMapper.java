package com.ventaproductos.product.mapper;

import org.mapstruct.Mapper;

import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.entity.ProductDTOSave;
import com.ventaproductos.product.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    ProductDTO toDTO(ProductEntity entity);
    ProductEntity toEntity(ProductDTOSave dto);

}
