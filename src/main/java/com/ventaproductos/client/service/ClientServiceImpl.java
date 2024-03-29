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
    public ClientDTO getByEmail(String email) {
        return mapper.toDTO(repository.findByEmail(email));
    }

    @Override
    public ClientDTO getByAddress(String address) {
        return mapper.toDTO(repository.findByAddress(address));
    }

    @Override
    public List<ClientDTO> getAllByName(String name) {
        List<ClientDTO> nameDTOList = new ArrayList<>();
        repository.findAllByName(name).stream().forEach( client -> {
            nameDTOList.add(mapper.toDTO(client));
        } );
        return nameDTOList;
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
    public Optional<ClientDTO> get(Integer id) {
        Optional<ClientEntity> client = repository.findById(id);
        if (client.isPresent()) {
            ClientEntity clientToDTO = client.get();
            return Optional.of(mapper.toDTO(clientToDTO));
        }
        return Optional.empty();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ClientDTO> update(Integer id, ClientDTO client) {
        Optional<ClientEntity> getClient = repository.findById(id);
        if (getClient.isPresent()) {
            ClientEntity clientDb = getClient.get();
            clientDb.setName(client.getName());
            clientDb.setEmail(client.getEmail());
            clientDb.setAddress(client.getAddress());

            ClientEntity updateCliente = repository.save(clientDb);

            return Optional.of(mapper.toDTO(updateCliente));
        }

        return Optional.empty();

    }

    @SuppressWarnings("null")
    @Override
    public ClientDTO create(ClientDTO client) {
        ClientEntity clientEntity = mapper.toEntity(client);
        return mapper.toDTO(repository.save(clientEntity));
    }
    
}
