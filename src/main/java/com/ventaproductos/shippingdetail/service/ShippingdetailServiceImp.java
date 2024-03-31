package com.ventaproductos.shippingdetail.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.order.mapper.OrderMapper;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;
import com.ventaproductos.shippingdetail.mapper.ShippingDetailMapper;
import com.ventaproductos.shippingdetail.repository.ShippingDetailRepository;

@Service
public class ShippingdetailServiceImp implements ShippingdetailServiceInterface{

    private ShippingDetailRepository repository;
    private ShippingDetailMapper shippingDetailMapper;
    private OrderMapper orderMapper;

    public ShippingdetailServiceImp(
            ShippingDetailRepository repository, 
            ShippingDetailMapper shippingDetailMapper,
            OrderMapper orderMapper
        ) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    @Override
    public ShippingDetailDTO create(ShippingDetailDTO shippingDetail) {
        ShippingDetailEntity shippingDetailEntity = shippingDetailMapper.toEntity(shippingDetail);
        return shippingDetailMapper.toDTO(repository.save(shippingDetailEntity));
    }

    @Override
    public List<ShippingDetailDTO> getAll() {
        List<ShippingDetailDTO> shippingDetailDTOList = new ArrayList<>();
        repository.findAll().stream().forEach( shippingDetailDb -> {
            shippingDetailDTOList.add( shippingDetailMapper.toDTO(shippingDetailDb) );
        } );

        return shippingDetailDTOList;
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ShippingDetailDTO> get(Integer id) {
        ShippingDetailEntity shippingDetailEntity = repository.findById(id).get();
        return Optional.of(shippingDetailMapper.toDTO(shippingDetailEntity));
    }

    @SuppressWarnings("null")
    @Override
    public Optional<ShippingDetailDTO> update(Integer id, ShippingDetailDTO shippingDetail) {
        return repository.findById(id).map( shippingDetailDb ->{

            shippingDetailDb.setAddress(shippingDetail.getAddress());
            shippingDetailDb.setConveyor(shippingDetail.getConveyor());
            shippingDetailDb.setGuideNumber(shippingDetail.getGuideNumber());
            shippingDetailDb.setOrder(orderMapper.toEntity(shippingDetail.getOrder()));

            return shippingDetailMapper.toDTO(repository.save(shippingDetailDb));
        });
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ShippingDetailDTO getByOrderId(Integer id) {
       return shippingDetailMapper.toDTO(repository.findByOrderId(id));
    }

    @Override
    public List<ShippingDetailDTO> getByConveyor(String conveyor) {
        List<ShippingDetailDTO> shippingDetailDTOList = new ArrayList<>();
        repository.findByConveyor(conveyor).stream().forEach( shippingDetailDb -> {
            shippingDetailDTOList.add( shippingDetailMapper.toDTO(shippingDetailDb) );
        } );

        return shippingDetailDTOList;
        
    }

    @Override
    public List<ShippingDetailDTO> getByOrderByStatus(String status) {
        return shippingDetailMapper.toDTOList(repository.findByOrderByStatus(status));
    }
    
}
