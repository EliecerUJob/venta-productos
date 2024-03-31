package com.ventaproductos.orderitem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.orderitem.entity.OrderItemEntity;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "products", ignore=true)
    OrderItemDTO toDTO(OrderItemEntity orderItemEntity);

    @Mapping(target = "products", ignore=true)
    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);
    
    List<OrderItemEntity> toOrderItemEntityList(List<OrderItemDTO> orderItemDTO);
    List<OrderItemDTO> tOrderItemDTOList(List<OrderItemEntity> orderItemEntity);

}