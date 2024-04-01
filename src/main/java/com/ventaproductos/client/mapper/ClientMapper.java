package com.ventaproductos.client.mapper;

import org.mapstruct.Mapper;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientDTOSave;
import com.ventaproductos.client.entity.ClientEntity;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    
    ClientEntity toEntity(ClientDTOSave dto);

    ClientDTO toDTO(ClientEntity clientToDTO);

}
