package com.ventaproductos.shippingdetail.service;

import java.util.List;
import java.util.Optional;

import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;

public interface ShippingdetailServiceInterface {

    ShippingDetailDTO create(ShippingDetailDTO shippingDetail);
    List<ShippingDetailDTO> getAll();
    Optional<ShippingDetailDTO> get(Integer id);
    Optional<ShippingDetailDTO> update(Integer id, ShippingDetailDTO shippingDetail);
    void delete(Integer id);

    // ShippingDetailDTO getByOrderById(Integer id);
    List<ShippingDetailDTO> getByConveyor(String conveyor);
    //List<ShippingDetailEntity> getByOrderByStatus(String status);  Convertir string status a enum

}
