package com.ventaproductos.payment.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.order.mapper.OrderMapper;
import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.payment.entity.PaymentMethodEnum;
import com.ventaproductos.payment.mapper.PaymentMapper;
import com.ventaproductos.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentServiceInterface{

    private PaymentRepository repository;
    private PaymentMapper paymentMapper;
    private OrderMapper orderMapper;

    public PaymentServiceImp(PaymentRepository repository, OrderMapper orderMapper, PaymentMapper paymentMapper) {
        this.repository = repository;
        this.orderMapper = orderMapper;
        this.paymentMapper = paymentMapper;
    }

    @SuppressWarnings("null")
    @Override
    public PaymentDTO create(PaymentDTO payment) {
        PaymentEntity paymentEntity = paymentMapper.toEntity(payment);
        return paymentMapper.toDTO(repository.save(paymentEntity));
    }

    @SuppressWarnings("null")
    @Override
    public Optional<PaymentDTO> get(Integer id) {
        PaymentEntity paymentEntity = repository.findById(id).get();
        return Optional.of(paymentMapper.toDTO(paymentEntity));
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<PaymentDTO> paymentDTOList = new ArrayList<>();
        repository.findAll().stream().forEach( paymentDb -> {
            paymentDTOList.add(paymentMapper.toDTO(paymentDb));
        } );

        return paymentDTOList;
    }

    @SuppressWarnings("null")
    @Override
    public Optional<PaymentDTO> update(Integer id, PaymentDTO payment) {
        return repository.findById(id).map( paymentDb -> {
            paymentDb.setOrder( orderMapper.toEntity(payment.getOrder()) );
            paymentDb.setPaymentDate(payment.getPaymentDate());
            paymentDb.setPaymentMethod(PaymentMethodEnum.valueOf(payment.getPaymentMethod()));
            paymentDb.setTotalPayment(payment.getTotalPayment());

            return paymentMapper.toDTO(repository.save(paymentDb));
        });
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<PaymentDTO> getByPaymentDateBetween(LocalDate start, LocalDate end) {
        List<PaymentDTO> paymentDTOList = new ArrayList<>();
        repository.findByPaymentDateBetween(start, end).stream().forEach( paymentDb -> {
            paymentDTOList.add( paymentMapper.toDTO(paymentDb) );
        } );

        return paymentDTOList;
    }

    // @Override
    // public PaymentEntity getByOrderByIdAndPaymentMethod(Integer id, String pMethod) {
    //     return repository.findByOrderByIdAndPaymentMethod(id, pMethod);
    // }
    
}
