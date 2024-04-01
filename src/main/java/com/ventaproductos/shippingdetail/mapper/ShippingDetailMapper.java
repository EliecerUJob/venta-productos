package com.ventaproductos.shippingdetail.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTOSave;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

@Mapper(componentModel = "spring")
public interface ShippingDetailMapper {
    
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    ShippingDetailDTO toDTO(ShippingDetailEntity entity);

    @Mapping(target = "id", ignore=true)
    ShippingDetailEntity toEntity(ShippingDetailDTOSave dto);
    
}
