package com.ventaproductos.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientDTOSave;
import com.ventaproductos.client.service.ClientServiceImpl;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RestController
@RequestMapping("/api/v1/costumers")
public class ClientController {
    
    private final ClientServiceImpl clientService;
    
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }
    
    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getAll(){
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClientDTO>> get(@PathVariable("id") Integer id){
        return new ResponseEntity<>(clientService.get(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDTO> getByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(clientService.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<ClientDTO> getByAddress(@RequestParam String cityName){
        return new ResponseEntity<>(clientService.getByAddress(cityName), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<Optional<ClientDTOSave>> create (@RequestBody ClientDTOSave dto) throws Exception {
        clientService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<ClientDTO>> update(@PathVariable("id") Integer id, @RequestBody ClientDTO dto){
        clientService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
