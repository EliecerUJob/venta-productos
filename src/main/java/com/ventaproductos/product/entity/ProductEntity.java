package com.ventaproductos.product.entity;


import com.ventaproductos.orderitem.entity.OrderItemEntity;
import jakarta.persistence.*;
import lombok.*;

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
    private double price;
    private int stock;

    @OneToOne(mappedBy = "product")
    private OrderItemEntity orderItem;

}
