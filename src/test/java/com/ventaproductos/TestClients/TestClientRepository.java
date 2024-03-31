package com.ventaproductos.TestClients;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import com.ventaproductos.AbstractIntegrationTest;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.repository.ClientRepository;

public class TestClientRepository extends AbstractIntegrationTest{
    @Autowired
    private ClientRepository clientRepository;
   

    // public TestClientRepository(ClientRepository clientRepository) {
    //     this.clientRepository = clientRepository;
    // }

   
    // public TestClientRepository(){
        
    // }

    @SuppressWarnings("null")
    void initMockClients(){
        ClientEntity clientEntity = ClientEntity.builder()
        .name("Sergio")
        .address("#123")
        .email("almazosergio@gmail.com")
        .build();
        clientRepository.save(clientEntity);

        ClientEntity clientEntity2 = ClientEntity.builder()
        .name("Jose")
        .address("#456")
        .email("Jose@gmail.com")
        .build();
        clientRepository.save(clientEntity2);
        clientRepository.flush();
    }

    @BeforeEach
    void setUp() {
        clientRepository.deleteAll();
    }

    @SuppressWarnings("null")
    @Test
    public void giveAclient_whenSave_thenClientWithId() {
        // given
        ClientEntity clientEntity = ClientEntity.builder()
               .name("Jose")
               .address("#456")
               .email("Jose@gmail.com")
               .build();
        // when
        ClientEntity clientSaved = clientRepository.save(clientEntity);
        // then
        assertThat(clientSaved.getId()).isNotNull();
    }

}
