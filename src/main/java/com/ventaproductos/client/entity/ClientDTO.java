package com.ventaproductos.client.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

import com.ventaproductos.order.entity.OrderDTO;

@Getter
@Setter
public class ClientDTO {
    
    private Integer id;
    private String name;
    private String email;
    private String address;

    private List<OrderDTO> orders;

}
