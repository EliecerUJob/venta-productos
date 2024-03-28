package com.ventaproductos.shippingdetail.mapper;

import org.mapstruct.Mapper;

import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

@Mapper
public interface ShippingDetailMapper {
    
    ShippingDetailDTO toDTO(ShippingDetailEntity entity);
    ShippingDetailEntity toEntity(ShippingDetailDTO dto);

}
