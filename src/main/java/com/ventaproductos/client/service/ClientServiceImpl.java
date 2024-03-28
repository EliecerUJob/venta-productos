package com.ventaproductos.client.service;

import java.util.*;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.mapper.ClientMapper;
import com.ventaproductos.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientServiceInterface{

    private ClientRepository repository;
    private ClientMapper mapper;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ClientEntity getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public ClientEntity getByAddress(String address) {
        return repository.findByAddress(address);
    }

    @Override
    public List<ClientEntity> getAllByName(String name) {
        return this.repository.findAllByName(name);
    }

    @Override
    public List<ClientDTO> getAll() {
        List<ClientDTO> clientDTOs = new ArrayList<>();
        repository.findAll().stream().forEach( client -> {
            clientDTOs.add(mapper.toDTO(client));
        });

        return clientDTOs;
    }

    @Override
    @SuppressWarnings("null")
    public ClientDTO get(Integer id) {
        Optional<ClientEntity> client = repository.findById(id);
        if (client.isPresent()) {
            ClientEntity clientToDTO = client.get();
            return mapper.toDTO(clientToDTO);
        }
        return new ClientDTO();
    }

    @Override
    public Optional<ClientDTO> update(Integer id, ClientDTO client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ClientEntity create(ClientDTO client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    
}
