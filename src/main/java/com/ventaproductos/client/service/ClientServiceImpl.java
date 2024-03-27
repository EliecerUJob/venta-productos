package com.ventaproductos.client.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientServiceInterface{

    private ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientEntity> getAll() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ClientEntity> get(Integer id) {
        return repository.findById(id);
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ClientEntity> update(Integer id, ClientEntity client) {
        return repository.findById(id).map( clientDb -> {
            clientDb.setName(client.getName());
            clientDb.setEmail(client.getEmail());
            clientDb.setAddress(client.getAddress());
            return repository.save(clientDb);
        } );
    }

    @SuppressWarnings("null")
    @Override
    public ClientEntity create(ClientEntity client) {
        return repository.save(client);
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
    
}
