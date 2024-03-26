package com.ventaproductos.shippingdetail.entity;

import com.ventaproductos.order.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shipping_details")
public class ShippingDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String conveyor; //transportadora
    @Column(name = "guide_number")
    private int guideNumber;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}
