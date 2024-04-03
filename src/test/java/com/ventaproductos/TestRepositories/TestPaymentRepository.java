package com.ventaproductos.TestRepositories;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ventaproductos.AbstractIntegrationTest;
import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.entity.OrderStatusEnum;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.payment.entity.PaymentMethodEnum;
import com.ventaproductos.payment.repository.PaymentRepository;

public class TestPaymentRepository extends AbstractIntegrationTest{
    
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TestPaymentRepository(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @BeforeEach
    void setup(){
        paymentRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @SuppressWarnings("null")
    void init(){
        OrderEntity order = OrderEntity.builder()
               .dateOrder(LocalDate.now())
               .status(OrderStatusEnum.valueOf("PENDING"))
               .build();
        
        orderRepository.save(order);

        PaymentEntity orderEntity = PaymentEntity.builder()
                .order(order)
                .paymentDate(order.getDateOrder())
                .paymentMethod(PaymentMethodEnum.valueOf("NEQUI"))
                .totalPayment(12000.0)
                .build();
        
        paymentRepository.save(orderEntity);
    }

    @Test
    void givenPayment_whenFindByPaymentDateBetween_thenReturnPayment(){
        //Given
        init();
        //When
        List<PaymentEntity> payments = paymentRepository.findByPaymentDateBetween(LocalDate.of(2010, 01, 01), LocalDate.of(2550, 01, 1));
        //Then
        assertEquals(1, payments.size());
    }

    @Test
    void givenPayment_whenFindByPaymentDateBetween_thenReturnEmpty(){
        //Given
        init();
        //When
        List<PaymentEntity> payments = paymentRepository.findByPaymentDateBetween(LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 1));
        //Then
        assertEquals(0, payments.size());
    }

    @Test
    void givenPayment_whenFindByOrderIdAndPaymentMethod_thenReturnPayment(){
        //Given
        init();
        //When
        List<PaymentEntity> payments = paymentRepository.findByOrderIdAndPaymentMethod(orderRepository.findAll().get(0).getId(), PaymentMethodEnum.valueOf("NEQUI"));
        //Then
        assertEquals(1, payments.size());
    }

    @Test
    void givenPayment_whenFindByOrderIdAndPaymentMethod_thenReturnEmpty(){
        //Given
        init();
        //When
        List<PaymentEntity> payments = paymentRepository.findByOrderIdAndPaymentMethod(orderRepository.findAll().get(0).getId(), PaymentMethodEnum.valueOf("DAVIDPLATA"));
        //Then
        assertEquals(0, payments.size());
    }

}
