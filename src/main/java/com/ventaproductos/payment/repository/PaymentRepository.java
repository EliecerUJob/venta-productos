package com.ventaproductos.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.payment.entity.PaymentMethodEnum;

import java.util.List;
import java.time.LocalDate;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{
    
    List<PaymentEntity> findByPaymentDateBetween(LocalDate start, LocalDate end);
    List<PaymentEntity> findByOrderIdAndPaymentMethod(Integer id, PaymentMethodEnum pMethod);

}
