package com.ventaproductos.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientDTOSave;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    
    @Mapping(target = "orders", ignore = true)
    ClientEntity toEntity(ClientDTO dto);

    ClientEntity toEntitySave(ClientDTOSave dto);

    @Mapping(target = "orders", ignore = true)
    ClientDTO toDTO(ClientEntity clientToDTO);

    ClientDTOSave toDTOSave(ClientEntity clientToDTO);
        
    List<OrderEntity> toOrderEntityList(List<OrderDTO> orders);
    List<OrderDTO> toOrderDTOList(List<OrderEntity> orders);

}
