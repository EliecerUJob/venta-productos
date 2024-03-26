package com.ventaproductos.product.entity;


import com.ventaproductos.orderitem.entity.OrderItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private int stock;

    @ManyToMany(mappedBy = "products")
    private List<OrderItemEntity>orderItems;

}
