package com.ventaproductos.TestRepositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.ventaproductos.AbstractIntegrationTest;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.repository.ClientRepository;

public class TestClientRepository extends AbstractIntegrationTest{
    
    private final ClientRepository clientRepository;
    
    @Autowired
    public TestClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    void init(){

        ClientEntity client = ClientEntity.builder()
                    .name("Eliecer U")
                    .email("eliecer@gmail.com")
                    .address("La U")
                    .build();
                
        clientRepository.save(client);
        clientRepository.flush();
    }

    @BeforeEach
    void setUp() {
        clientRepository.deleteAll();
    }

    @Test
    void givenClient_whenFindByEmail_thenReturnClient(){
        //Given
        init();
        //When
        ClientEntity client = clientRepository.findByEmail("eliecer@gmail.com");
        //Then
        assertThat(client).isNotNull();
    }

    @Test
    void givenClient_whenFindByEmail_thenReturnEmpty(){
        //Given
        init();
        //When
        ClientEntity client = clientRepository.findByEmail("<EMAIL>");
        //Then
        assertThat(client).isNull();
    }

    @Test
    void givenClient_whenFindByAddress_thenReturnClient(){
        //Given
        init();
        //When
        ClientEntity client = clientRepository.findByAddress("La U");
        //Then
        assertThat(client).isNotNull();
    }

    @Test
    void givenClient_whenFindByAddress_thenReturnEmpty(){
        //Given
        init();
        //When
        ClientEntity client = clientRepository.findByAddress("La V");
        //Then
        assertThat(client).isNull();
    }

    @Test
    void givenClient_whenFindByNameStartingWith_thenReturnClient(){
        //Given
        init();
        //When
        List<ClientEntity> clients = clientRepository.findByNameStartingWith("E");
        //Then
        assertEquals(1,clients.size());
    }

    @Test
    void givenClient_whenFindByNameStartingWith_thenReturnEmpty(){
        //Given
        init();
        //When
        List<ClientEntity> clients = clientRepository.findByNameStartingWith("Z");
        //Then
        assertEquals(0,clients.size());
    }

}
