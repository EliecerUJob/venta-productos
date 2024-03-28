package com.ventaproductos.shippingdetail.service;

import java.util.List;
import java.util.Optional;

import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;

public interface ShippingdetailServiceInterface {

    ShippingDetailEntity create(ShippingDetailEntity shippingDetail);
    List<ShippingDetailEntity> getAll();
    Optional<ShippingDetailEntity> get(Integer id);
    Optional<ShippingDetailEntity> update(Integer id, ShippingDetailEntity shippingDetail);
    void delete(Integer id);

    // ShippingDetailEntity getByOrderById(Integer id);
    List<ShippingDetailEntity> getByConveyor(String conveyor);
    //List<ShippingDetailEntity> getByOrderByStatus(String status);  Convertir string status a enum

}
