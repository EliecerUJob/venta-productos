package com.ventaproductos.client.service;

import java.util.List;
import java.util.Optional;

import com.ventaproductos.client.entity.ClientEntity;

public interface ClientServiceInterface {
    
    List<ClientEntity> getAll();
    Optional<ClientEntity> get(Integer id);
    void delete(Integer id);
    Optional<ClientEntity> update(Integer id, ClientEntity client);
    ClientEntity create(ClientEntity client);

    ClientEntity getByEmail(String email);
    ClientEntity getByAddress(String address);
    List<ClientEntity> getAllByName(String name);

}
