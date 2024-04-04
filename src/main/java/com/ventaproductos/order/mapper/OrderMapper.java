package com.ventaproductos.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderDTORecuperate;
import com.ventaproductos.order.entity.OrderDTOSave;
import com.ventaproductos.order.entity.OrderEntity;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    @Mapping(source="clientId", target="client.id")
    OrderEntity toEntity(OrderDTOSave dto);

    @Mapping(source="client.id", target="clientId")
    OrderDTO toDTO(OrderEntity entity);

    @Mapping(source="client.id", target="clientId")
    OrderDTORecuperate toDTORecuperate(OrderEntity entity);
    
}
