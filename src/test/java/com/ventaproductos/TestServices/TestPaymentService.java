package com.ventaproductos.TestServices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.entity.OrderStatusEnum;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.payment.entity.PaymentDTOSave;
import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.payment.entity.PaymentMethodEnum;
import com.ventaproductos.payment.entity.PaymentTotalItems;
import com.ventaproductos.payment.mapper.PaymentMapper;
import com.ventaproductos.payment.repository.PaymentRepository;
import com.ventaproductos.payment.service.PaymentServiceImp;

@ExtendWith(MockitoExtension.class)
public class TestPaymentService {
    
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PaymentMapper paymentMapper;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PaymentTotalItems paymentTotalItems;
    @InjectMocks
    private PaymentServiceImp paymentService;
    private PaymentEntity payment;
    private OrderEntity order;
    @BeforeEach
    void setUp() {
        order = OrderEntity.builder()
                .id(1)
                .status(OrderStatusEnum.PENDING)
                .dateOrder(LocalDate.now())
                .build();

        payment = PaymentEntity.builder()
                .id(1)
                .totalPayment(100.0)
                .paymentDate(LocalDate.now())
                .paymentMethod(PaymentMethodEnum.CREDITCARD)
                .order(order)
                .build();   
    }

    @Test
    void givenPayment_whenCreatePayment_thenReturnCreatePayment() {
        // Given
        PaymentDTOSave paymentDTOSave = PaymentDTOSave.builder()
                .orderId(payment.getOrder().getId())
                .paymentDate(payment.getPaymentDate())
                .paymentMethod(payment.getPaymentMethod())
                .build();

        given(orderRepository.findById(order.getId())).willReturn(Optional.of(payment.getOrder()));
        given(paymentMapper.toEntity(paymentDTOSave)).willReturn(payment);
        given(paymentMapper.toDTO(payment)).willReturn(PaymentDTO.builder()
                .id(payment.getId())
                .totalPayment(payment.getTotalPayment())
                .orderId(payment.getOrder().getId())
                .paymentDate(payment.getPaymentDate())
                .paymentMethod(payment.getPaymentMethod())
                .build());

        // Then
        var paymentSave = paymentService.create(paymentDTOSave);

        // When
        assertThat(paymentSave).isNotNull();
        assertEquals(paymentSave.orderId(), payment.getOrder().getId());
        assertEquals(paymentSave.totalPayment(), payment.getTotalPayment());
        assertEquals(paymentSave.paymentDate(), payment.getPaymentDate());
        assertEquals(paymentSave.paymentMethod(), payment.getPaymentMethod());
    }


}
