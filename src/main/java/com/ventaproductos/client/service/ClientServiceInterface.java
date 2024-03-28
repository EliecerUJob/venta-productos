package com.ventaproductos.client.service;

import java.util.List;
import java.util.Optional;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientEntity;

public interface ClientServiceInterface {
    
    List<ClientDTO> getAll();
    ClientDTO get(Integer id);
    void delete(Integer id);
    Optional<ClientDTO> update(Integer id, ClientDTO client);
    ClientEntity create(ClientDTO client);

    ClientEntity getByEmail(String email);
    ClientEntity getByAddress(String address);
    List<ClientEntity> getAllByName(String name);

}
