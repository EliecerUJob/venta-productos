package com.ventaproductos.product.entity;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

import com.ventaproductos.orderitem.entity.OrderItemDTO;

@Getter
@Setter
public class ProductDTO {
    
    private Integer id;
    private String name;
    private BigDecimal price;
    private int stock;

    private List<OrderItemDTO> orderItems;
    
}
