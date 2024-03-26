package com.ventaproductos.orderitem.entity;

import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal quantity;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    //Foreign keys
    @ManyToOne
    @JoinColumn(name = "order_id", unique = true)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToMany
    @JoinTable(
            name = "order_item_product",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

}
