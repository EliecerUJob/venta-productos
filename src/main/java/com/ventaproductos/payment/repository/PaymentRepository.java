package com.ventaproductos.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventaproductos.payment.entity.PaymentEntity;
import java.util.List;
import java.time.LocalDate;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{
    
    List<PaymentEntity> findByPaymentDateBetween(LocalDate start, LocalDate end);
    PaymentEntity findByOrderByIdAndPaymentMethod(Integer id, String pMethod);

}
