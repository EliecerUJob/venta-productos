package com.ventaproductos.shippingdetail.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

@Mapper(componentModel = "spring")
public interface ShippingDetailMapper {
    
    ShippingDetailDTO toDTO(ShippingDetailEntity entity);
    ShippingDetailEntity toEntity(ShippingDetailDTO dto);

    List<ShippingDetailDTO> toDTOList(List<ShippingDetailEntity> entityList);
    
}
