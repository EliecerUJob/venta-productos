package com.ventaproductos.order.entity;

import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date_order")
    private LocalDate dateOrder;
    
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    // Foreign keys
    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;

    @OneToOne(mappedBy = "order")
    private PaymentEntity payment;

    @OneToOne(mappedBy = "order")
    private ShippingDetailEntity shippingDetail;

}
