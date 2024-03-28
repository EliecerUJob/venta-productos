package com.ventaproductos.shippingdetail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;
import com.ventaproductos.shippingdetail.repository.ShippingDetailRepository;

@Service
public class ShippingdetailServiceImp implements ShippingdetailServiceInterface{

    private ShippingDetailRepository repository;

    public ShippingdetailServiceImp(ShippingDetailRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    @Override
    public ShippingDetailEntity create(ShippingDetailEntity shippingDetail) {
        return repository.save(shippingDetail);
    }

    @Override
    public List<ShippingDetailEntity> getAll() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ShippingDetailEntity> get(Integer id) {
        return repository.findById(id);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ShippingDetailEntity> update(Integer id, ShippingDetailEntity shippingDetail) {
        return repository.findById(id).map( shippingDetailDb ->{

            shippingDetailDb.setAddress(shippingDetail.getAddress());
            shippingDetailDb.setConveyor(shippingDetail.getConveyor());
            shippingDetailDb.setGuideNumber(shippingDetail.getGuideNumber());
            shippingDetailDb.setOrder(shippingDetail.getOrder());

            return repository.save(shippingDetailDb);
        });
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // @Override
    // public ShippingDetailEntity getByOrderById(Integer id) {
    //    return repository.findByOrderById(id);
    // }

    @Override
    public List<ShippingDetailEntity> getByConveyor(String conveyor) {
        return repository.findByConveyor(conveyor);
    }

    // @Override
    // public List<ShippingDetailEntity> getByOrderByStatus(String status) {
    //     status = status.toUpperCase();
    //     OrderStatusEnum statusEnum = OrderStatusEnum.valueOf(status);
    //     return repository.findByOrderByStatus(statusEnum);
    // }
    
}
