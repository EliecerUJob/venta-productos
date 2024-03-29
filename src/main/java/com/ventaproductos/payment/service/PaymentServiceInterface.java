package com.ventaproductos.payment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ventaproductos.payment.entity.PaymentDTO;

public interface PaymentServiceInterface {
    
    PaymentDTO create(PaymentDTO payment);
    Optional<PaymentDTO> get(Integer id);
    List<PaymentDTO> getAll();
    Optional<PaymentDTO> update(Integer id, PaymentDTO payment);
    void delete(Integer id);

    List<PaymentDTO> getByPaymentDateBetween(LocalDate start, LocalDate end);
    // PaymentEntity getByOrderByIdAndPaymentMethod(Integer id, String pMethod);

}
