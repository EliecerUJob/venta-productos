package com.ventaproductos.payment.entity;

import com.ventaproductos.order.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal totalPayment;
    private LocalDate paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    //Foreign keys
    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}
