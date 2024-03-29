package com.ventaproductos.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderEntity;

@Mapper
public interface ClientMapper {
    
    @Mapping(target = "orders", ignore = true)
    ClientEntity toEntity(ClientDTO dto);

    @Mapping(target = "orders", ignore = true)
    ClientDTO toDTO(ClientEntity clientToDTO);
        
    List<OrderEntity> toOrderEntityList(List<OrderDTO> orders);
    List<OrderDTO> toOrderDTOList(List<OrderEntity> orders);

}
