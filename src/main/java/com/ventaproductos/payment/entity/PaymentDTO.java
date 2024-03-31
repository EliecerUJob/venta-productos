package com.ventaproductos.payment.entity;

import com.ventaproductos.order.entity.OrderDTO;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentDTO {
    
    private Integer id;
    private BigDecimal totalPayment;
    private LocalDate paymentDate;
    private String paymentMethod;

    private OrderDTO order;

}
