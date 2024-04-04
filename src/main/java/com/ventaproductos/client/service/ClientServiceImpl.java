package com.ventaproductos.client.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.Exceptions.ClientNotFoundException;
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

    @Override
    public void delete(Integer id) {
        ClientEntity entity = repository.findById(id).orElseThrow(ClientNotFoundException::new);
        repository.delete(entity);
    }

    @Override
    public ClientDTO getByEmail(String email) {
        ClientEntity entity = repository.findByEmail(email);
        return mapper.toDTO(entity);
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
    public ClientDTO get(Integer id) {
        ClientEntity entity = repository.findById(id).orElseThrow(ClientNotFoundException::new);
        return mapper.toDTO(entity);
    }

    @Override
    public ClientDTO update(Integer id, ClientDTOSave client) {
        
        return repository.findById(id).map( clientDb -> {
            clientDb.setName(client.name());
            clientDb.setEmail(client.email());
            clientDb.setAddress(client.address());

            repository.save(clientDb);
            return mapper.toDTO(clientDb);
        } ).orElseThrow(ClientNotFoundException::new);

    }

    @Override
    public ClientDTO create(ClientDTOSave client) {
        ClientEntity clientEntity = mapper.toEntity(client);
        return mapper.toDTO(repository.save(clientEntity));
    }
    
}
