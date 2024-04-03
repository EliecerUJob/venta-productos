package com.ventaproductos;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestsContainerConfig {
    
    PostgreSQLContainer<?> postgreSQLContainer(){
        return new PostgreSQLContainer<>("postgres:15-alpine");
    }

}
