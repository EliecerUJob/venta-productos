package com.ventaproductos.payment.mapper;

import org.mapstruct.Mapper;

import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.payment.entity.PaymentEntity;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    
    PaymentDTO toDTO(PaymentEntity entity);
    PaymentEntity toEntity(PaymentDTO dto);

}
