package com.ventaproductos.order.mapper;

import org.mapstruct.Mapper;

import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderDTORecuperate;
import com.ventaproductos.order.entity.OrderDTOSave;
import com.ventaproductos.order.entity.OrderEntity;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    OrderEntity toEntity(OrderDTOSave dto);
    OrderDTO toDTO(OrderEntity entity);
    OrderDTORecuperate toDTORecuperate(OrderEntity entity);
    
}
