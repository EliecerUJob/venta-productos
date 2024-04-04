package com.ventaproductos.shippingdetail.service;

import java.util.List;

import com.ventaproductos.order.entity.OrderStatusEnum;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTOSave;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTOUpdate;

public interface ShippingdetailServiceInterface {

    ShippingDetailDTO create(ShippingDetailDTOSave shippingDetail);
    List<ShippingDetailDTO> getAll();
    ShippingDetailDTO get(Integer id);
    ShippingDetailDTO update(Integer id, ShippingDetailDTOUpdate shippingDetail);
    void delete(Integer id);

    List<ShippingDetailDTO> getByOrderId(Integer id);
    List<ShippingDetailDTO> getByOrderStatus(OrderStatusEnum status);
    List<ShippingDetailDTO> getByConveyor(String conveyor);

}
