package com.ventaproductos.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderEntity;

@Mapper
public interface OrderMapper {
    
    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );

    @Mapping(target = "orderItems", ignore = true)
    OrderEntity toEntity(OrderDTO dto);
    @Mapping(target = "orderItems", ignore = true)
    OrderDTO toDTO(OrderEntity entity);

    List<OrderDTO> toOrderDTOList(List<OrderEntity> orderEntityList);
    List<OrderEntity> toOrderEntityList(List<OrderDTO> orderDTOList);

}
