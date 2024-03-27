package com.ventaproductos.payment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ventaproductos.payment.entity.PaymentEntity;

public interface PaymentServiceInterface {
    
    PaymentEntity create(PaymentEntity payment);
    Optional<PaymentEntity> get(Integer id);
    List<PaymentEntity> getAll();
    Optional<PaymentEntity> update(Integer id, PaymentEntity payment);
    void delete(Integer id);

    List<PaymentEntity> getByPaymentDateBetween(LocalDate start, LocalDate end);
    PaymentEntity getByOrderByIdAndPaymentMethod(Integer id, String pMethod);

}
