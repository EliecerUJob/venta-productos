package com.ventaproductos.client.service;

import java.util.*;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientDTOSave;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.mapper.ClientMapper;
import com.ventaproductos.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientServiceInterface{

    private ClientRepository repository;
    private ClientMapper mapper;

    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
        repository.findByNameStartingWith(name).stream().forEach( client -> {
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
    public ClientDTO update(Integer id, ClientDTOSave client) {
        
        return repository.findById(id).map( clientDb -> {
            clientDb.setName(client.name());
            clientDb.setEmail(client.email());
            clientDb.setAddress(client.address());

            repository.save(clientDb);
            return mapper.toDTO(clientDb);
        } ).orElseThrow();

    }

    @SuppressWarnings("null")
    @Override
    public ClientDTO create(ClientDTOSave client) {
        ClientEntity clientEntity = mapper.toEntity(client);
        repository.save(clientEntity);
        return mapper.toDTO(clientEntity);
    }
    
}
