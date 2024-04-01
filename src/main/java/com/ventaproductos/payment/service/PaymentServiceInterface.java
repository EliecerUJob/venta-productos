package com.ventaproductos.payment.service;

import java.time.LocalDate;
import java.util.List;

import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.payment.entity.PaymentDTOSave;
import com.ventaproductos.payment.entity.PaymentDTOUpdate;
import com.ventaproductos.payment.entity.PaymentMethodEnum;

public interface PaymentServiceInterface {
    
    PaymentDTO create(PaymentDTOSave payment);
    PaymentDTO get(Integer id);
    List<PaymentDTO> getAll();
    PaymentDTO update(Integer id, PaymentDTOUpdate payment);
    void delete(Integer id);

    List<PaymentDTO> getByPaymentDateBetween(LocalDate start, LocalDate end);
    List<PaymentDTO> getByOrderIdAndPaymentMethod(Integer id, PaymentMethodEnum pMethod);

}
