package com.ventaproductos.payment.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.order.mapper.OrderMapper;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.payment.entity.PaymentDTOSave;
import com.ventaproductos.payment.entity.PaymentDTOUpdate;
import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.payment.entity.PaymentMethodEnum;
import com.ventaproductos.payment.entity.PaymentTotalItems;
import com.ventaproductos.payment.mapper.PaymentMapper;
import com.ventaproductos.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentServiceInterface{

    private PaymentRepository repository;
    private PaymentMapper paymentMapper;
    private OrderRepository orderRepository;
    private PaymentTotalItems paymentTotalItems;

    public PaymentServiceImp(
            PaymentRepository repository, 
            OrderMapper orderMapper, 
            PaymentMapper paymentMapper,
            OrderRepository orderRepository,
            PaymentTotalItems paymentTotalItems
        ) {
        this.repository = repository;
        this.paymentMapper = paymentMapper;
        this.orderRepository = orderRepository;
        this.paymentTotalItems = paymentTotalItems;
    }

    @Override
    public PaymentDTO create(PaymentDTOSave paymentDTO) {

        var orderEntity = orderRepository.findById(paymentDTO.orderId()).orElseThrow();
        var paymentToSave = PaymentDTOSave.builder()
                    .orderId(paymentDTO.orderId())
                    .paymentDate(paymentDTO.paymentDate())
                    .paymentMethod(paymentDTO.paymentMethod())
                    .order(orderEntity)
                    .build();

        var payment = paymentMapper.toEntity(paymentToSave);
        return paymentMapper.toDTO(repository.save(payment));
        
    }

    @Override
    public PaymentDTO get(Integer id) {
        PaymentEntity paymentEntity = repository.findById(id).orElseThrow();
        return paymentMapper.toDTO(paymentEntity);
    }

    @Override
    public List<PaymentDTO> getAll() {
        var payments = repository.findAll();
        return payments.stream().map( paymentMapper::toDTO ).toList();
    }

    @Override
    public PaymentDTO update(Integer id, PaymentDTOUpdate payment) {
        return repository.findById(id).map( paymentDb -> {
            paymentDb.setOrder( orderRepository.findById(payment.orderId()).orElseThrow() );
            paymentDb.setPaymentDate(payment.paymentDate());
            paymentDb.setPaymentMethod(payment.paymentMethod());
            paymentDb.setTotalPayment( paymentTotalItems.totalItems(payment.orderId()) );

            return paymentMapper.toDTO(repository.save(paymentDb));
        }).orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<PaymentDTO> getByPaymentDateBetween(LocalDate start, LocalDate end) {
        var payments = repository.findByPaymentDateBetween(start, end);
        return payments.stream().map( paymentMapper::toDTO ).toList();
    }

    @Override
    public List<PaymentDTO> getByOrderIdAndPaymentMethod(Integer id, PaymentMethodEnum pMethod) {
        var payments = repository.findByOrderIdAndPaymentMethod(id, pMethod);
        return payments.stream().map( paymentMapper::toDTO ).toList();
    }
    
}
