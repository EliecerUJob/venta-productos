package com.ventaproductos.client.entity;

import lombok.Builder;
import java.util.*;

import com.ventaproductos.order.entity.OrderDTO;

@Builder
public record ClientDTO(
    Integer id,
    String name,
    String email,
    String address,
    List<OrderDTO> orders
){
    
    public List<OrderDTO> orders(){
        return Collections.unmodifiableList(orders);
    }

}
