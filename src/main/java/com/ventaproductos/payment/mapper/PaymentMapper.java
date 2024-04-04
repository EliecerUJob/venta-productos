package com.ventaproductos.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.payment.entity.PaymentDTOSave;
import com.ventaproductos.payment.entity.PaymentEntity;
import com.ventaproductos.payment.entity.PaymentTotalItems;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {

    @Autowired
    private PaymentTotalItems paymentTotalItems;
    
    @Mapping(target = "orderId", source = "order.id")
    public abstract PaymentDTO toDTO(PaymentEntity entity);

    @Mapping(target = "totalPayment", source = "orderId", qualifiedByName = "totalPayment")
    @Mapping(target = "id",ignore = true)
    public abstract PaymentEntity toEntity(PaymentDTOSave dto);

    @Named("totalPayment")
    public double totalPayment(Integer id){
        return paymentTotalItems.totalItems(id);
    }

}
