package com.ventaproductos.TestServices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ventaproductos.Exceptions.ClientNotFoundException;
import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientDTOSave;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.mapper.ClientMapper;
import com.ventaproductos.client.repository.ClientRepository;
import com.ventaproductos.client.service.ClientServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestClientService {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientEntity clientEntity;

    @BeforeEach
    void setup() {
        clientEntity = new ClientEntity();
        clientEntity.setId(1);
        clientEntity.setName("Eliecer");
        clientEntity.setAddress("Direccion");
        clientEntity.setEmail("e@gmail.com");
    }

    @Test
    void givenClient_whenGetAll_thenReturnClientDTOList(){
        // Given
        given(clientRepository.findAll()).willReturn(List.of(clientEntity));
        given(clientMapper.toDTO(clientEntity)).willReturn(ClientDTO.builder()
            .id(1)
            .name("Eliecer")
            .address("Direccion")
            .email("e@gmail.com")
            .build()
        );
        // When
        List<ClientDTO> clientDTOList = clientService.getAll();
        // Then
        assertThat(clientDTOList).isNotEmpty();
        assertThat(clientDTOList.get(0).id()).isEqualTo(1);
        assertThat(clientDTOList.get(0).name()).isEqualTo("Eliecer");
        assertThat(clientDTOList.get(0).address()).isEqualTo("Direccion");
        assertThat(clientDTOList.get(0).email()).isEqualTo("e@gmail.com");
    }

    @Test
    void givenClient_whenGetById_thenReturnClientDTO(){
        // Given
        given(clientRepository.findById(1)).willReturn(Optional.of(clientEntity));
        given(clientMapper.toDTO(clientEntity)).willReturn(ClientDTO.builder()
            .id(1)
            .name("Eliecer")
            .address("Direccion")
            .email("e@gmail.com")
            .build()
        );
        // When
        ClientDTO clientDTO = clientService.get(1);
        // Then
        assertThat(clientDTO).isNotNull();
        assertThat(clientDTO.id()).isEqualTo(1);
        assertThat(clientDTO.name()).isEqualTo("Eliecer");
        assertThat(clientDTO.address()).isEqualTo("Direccion");
        assertThat(clientDTO.email()).isEqualTo("e@gmail.com");
    }

    @Test
    void givenClient_whenGetById_thenReturnEmpty(){
        // Given
        Integer id = 1;
        given(clientRepository.findById(id)).willReturn(Optional.empty());
        // When
        // Then
        assertThrows(ClientNotFoundException.class, () -> clientService.get(id), "Client not found");
    }
    
    @Test
    void givenClientId_whenDeleteClient_thenClientDelete(){
        // Given
        Integer id = 1;
        given(clientRepository.findById(id)).willReturn(Optional.of(clientEntity));
        //When
        willDoNothing().given(clientRepository).delete(clientEntity);
        clientService.delete(id);
        // Then
        verify(clientRepository, times(1)).delete(clientEntity);
    }

    @Test
    void givenClientId_whenUpdateClient_thenClientUpdate(){
        // Given
        given(clientRepository.findById(1)).willReturn(java.util.Optional.of(clientEntity));
        given(clientRepository.save(clientEntity)).willReturn(clientEntity);
        given(clientMapper.toDTO(clientEntity)).willReturn(ClientDTO.builder().id(1).name("Eliecer").email("e@gmail.com").address("Parques de bolivar").build());
        // When
        var customer = clientService.update(1, ClientDTOSave.builder().name("Eliecer").email("e@gmail.com").address("Parques de bolivar").build());
        // Then
        assertThat(customer.id()).isEqualTo(1L);
        assertThat(customer.name()).isEqualTo("Eliecer");
        assertThat(customer.email()).isEqualTo("e@gmail.com");
        assertThat(customer.address()).isEqualTo("Parques de bolivar");

    }

    @Test
    void givenClientId_whenUpdateClient_thenClientNotUpdate(){
        //Given
        Integer id = 1;
        given(clientRepository.findById(id)).willReturn(Optional.empty());
        // When
        // Then
        assertThrows(ClientNotFoundException.class, () -> clientService.update(id, ClientDTOSave.builder().build()), "Client not found");
    } 

    @Test
    void givenClient_whenCreateClient_thenClientCreate(){
        // Given
        ClientDTOSave clientDTOSave = ClientDTOSave.builder().name("Eliecer").email("e@gmail.com").address("Parques de bolivar").build();
        given(clientMapper.toEntity(clientDTOSave)).willReturn(clientEntity);
        given(clientRepository.save(clientEntity)).willReturn(clientEntity);
        given(clientMapper.toDTO(clientEntity)).willReturn(ClientDTO.builder().id(1).name("Eliecer").email("e@gmail.com").address("Parques de bolivar").build());
        // When
        var found = clientService.create(clientDTOSave);
        // Then
        assertThat(found).isNotNull();
        assertEquals(found.id(), 1);
        assertEquals(found.name(), "Eliecer");
        assertEquals(found.email(), "e@gmail.com");
        assertEquals(found.address(), "Parques de bolivar");
    }

    @Test
    void givenClient_whenGetByEmail_thenReturnClientDTO(){
        // Given
        given(clientRepository.findByEmail("e@gmail.com")).willReturn(clientEntity);
        given(clientMapper.toDTO(clientEntity)).willReturn(ClientDTO.builder()
            .id(1)
            .name("Eliecer")
            .address("Direccion")
            .email("e@gmail.com")
            .build()
        );
        // When
        ClientDTO clientDTO = clientService.getByEmail("e@gmail.com");
        // Then
        assertThat(clientDTO).isNotNull();
        assertThat(clientDTO.id()).isEqualTo(1);
        assertThat(clientDTO.name()).isEqualTo("Eliecer");
        assertThat(clientDTO.address()).isEqualTo("Direccion");
        assertThat(clientDTO.email()).isEqualTo("e@gmail.com");
    }

    @Test
    void givenClient_whenGetByAdress_thenReturnClientDTO(){
        // Given
        given(clientRepository.findByAddress("Direccion")).willReturn(clientEntity);
        given(clientMapper.toDTO(clientEntity)).willReturn(ClientDTO.builder()
            .id(1)
            .name("Eliecer")
            .address("Direccion")
            .email("e@gmail.com")
            .build()
        );
        // When
        ClientDTO clientDTO = clientService.getByAddress("Direccion");
        // Then
        assertThat(clientDTO).isNotNull();
        assertThat(clientDTO.id()).isEqualTo(1);
        assertThat(clientDTO.name()).isEqualTo("Eliecer");
        assertThat(clientDTO.address()).isEqualTo("Direccion");
        assertThat(clientDTO.email()).isEqualTo("e@gmail.com");
    }

    @Test
    void givenClient_whenGetByAllName_thenReturnClientDTO(){
        // Given
        given(clientRepository.findByNameStartingWith("Eliecer")).willReturn(List.of(clientEntity));
        given(clientMapper.toDTO(clientEntity)).willReturn(ClientDTO.builder()
            .id(1)
            .name("Eliecer")
            .address("Direccion")
            .email("e@gmail.com")
            .build()
        );
        // When
        List<ClientDTO> clientDTO = clientService.getAllByName("Eliecer");
        // Then
        assertThat(clientDTO).isNotNull();
        assertThat(clientDTO.get(0).id()).isEqualTo(1);
        assertThat(clientDTO.get(0).name()).isEqualTo("Eliecer");
        assertThat(clientDTO.get(0).address()).isEqualTo("Direccion");
        assertThat(clientDTO.get(0).email()).isEqualTo("e@gmail.com");
    }

}