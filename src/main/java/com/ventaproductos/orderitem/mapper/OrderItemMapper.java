package com.ventaproductos.orderitem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.orderitem.entity.OrderItemDTOSave;
import com.ventaproductos.orderitem.entity.OrderItemEntity;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "orderId", source = "order.id")
    OrderItemDTO toDTO(OrderItemEntity orderItemEntity);

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "order.id", source = "orderId")
    @Mapping(target = "id", ignore=true)
    OrderItemEntity toEntity(OrderItemDTOSave orderItemDTO);
    
}
