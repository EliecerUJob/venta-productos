package com.ventaproductos.client.repository;
import com.ventaproductos.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    ClientEntity findByEmail(String email);
    ClientEntity findByAddress(String email);
    List<ClientEntity> findByNameStartingWith(String name);
    
}
