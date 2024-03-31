package com.ventaproductos.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.service.ClientServiceImpl;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    
    private final ClientServiceImpl clientService;
    
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/prueba")
    public String postMethodName() {
        return "prueba";
    }

    @SuppressWarnings("unchecked")
    @PostMapping()
    public ResponseEntity<ClientDTO> save (@RequestBody ClientDTO dto)  throws IOException {
        clientService.create(dto);
        return (ResponseEntity<ClientDTO>) ResponseEntity.status(HttpStatus.CREATED);
    }
    

}
