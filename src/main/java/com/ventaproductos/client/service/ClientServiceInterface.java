package com.ventaproductos.client.service;

import java.util.List;
import java.util.Optional;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientDTOSave;

public interface ClientServiceInterface {
    
    List<ClientDTO> getAll();
    Optional<ClientDTO> get(Integer id);
    void delete(Integer id);
    Optional<ClientDTO> update(Integer id, ClientDTO client);
    Optional<ClientDTOSave> create(ClientDTOSave client);

    ClientDTO getByEmail(String email);
    ClientDTO getByAddress(String address);
    List<ClientDTO> getAllByName(String name);

}
