package com.ventaproductos.payment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentServiceInterface{

    private PaymentRepository repository;

    public PaymentServiceImp(PaymentRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    @Override
    public PaymentEntity create(PaymentEntity payment) {
        return repository.save(payment);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<PaymentEntity> get(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<PaymentEntity> getAll() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<PaymentEntity> update(Integer id, PaymentEntity payment) {
        return repository.findById(id).map( paymentDb -> {
            paymentDb.setOrder(payment.getOrder());
            paymentDb.setPaymentDate(payment.getPaymentDate());
            paymentDb.setPaymentMethod(payment.getPaymentMethod());
            paymentDb.setTotalPayment(payment.getTotalPayment());

            return repository.save(paymentDb);
        });
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<PaymentEntity> getByPaymentDateBetween(LocalDate start, LocalDate end) {
        return repository.findByPaymentDateBetween(start, end);
    }

    // @Override
    // public PaymentEntity getByOrderByIdAndPaymentMethod(Integer id, String pMethod) {
    //     return repository.findByOrderByIdAndPaymentMethod(id, pMethod);
    // }
    
}
