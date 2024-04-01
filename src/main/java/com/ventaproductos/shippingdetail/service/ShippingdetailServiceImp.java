package com.ventaproductos.shippingdetail.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.repository.ProductRepository;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTOSave;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTOUpdate;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;
import com.ventaproductos.shippingdetail.mapper.ShippingDetailMapper;
import com.ventaproductos.shippingdetail.repository.ShippingDetailRepository;

@Service
public class ShippingdetailServiceImp implements ShippingdetailServiceInterface{

    private ShippingDetailRepository repository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private ShippingDetailMapper shippingDetailMapper;

    public ShippingdetailServiceImp(
            ShippingDetailRepository repository, 
            OrderRepository orderRepository,
            ShippingDetailMapper shippingDetailMapper, 
            ProductRepository productRepository
        ) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.shippingDetailMapper = shippingDetailMapper;
    }

    @SuppressWarnings("null")
    @Override
    public ShippingDetailDTO create(ShippingDetailDTOSave shippingDetail) {
        
        OrderEntity orderEntity = orderRepository.findById(shippingDetail.orderId()).orElseThrow();
        ProductEntity productEntity = productRepository.findById(shippingDetail.productId()).orElseThrow();

        ShippingDetailDTOSave shippingDetailDTO = ShippingDetailDTOSave.builder()
               .product(productEntity)
               .order(orderEntity)
               .orderId(shippingDetail.orderId())
               .productId(shippingDetail.productId())
               .address(shippingDetail.address())
               .conveyor(shippingDetail.conveyor())
               .guideNumber(shippingDetail.guideNumber())
               .build();
        
        var shippingDetailSave = repository.save(shippingDetailMapper.toEntity(shippingDetailDTO));
        return shippingDetailMapper.toDTO(shippingDetailSave);

    }

    @Override
    public List<ShippingDetailDTO> getAll() {
        var shippingDetailList = repository.findAll();
        return shippingDetailList.stream().map( shippingDetailMapper::toDTO ).toList();
    }

    @SuppressWarnings("null")
    @Override
    public ShippingDetailDTO get(Integer id) {
        ShippingDetailEntity shippingDetailEntity = repository.findById(id).get();
        return shippingDetailMapper.toDTO(shippingDetailEntity);
    }

    @SuppressWarnings("null")
    @Override
    public ShippingDetailDTO update(Integer id, ShippingDetailDTOUpdate shippingDetail) {
        return repository.findById(id).map( shippingDetailDb ->{

            shippingDetailDb.setAddress(shippingDetail.address());
            shippingDetailDb.setConveyor(shippingDetail.conveyor());
            shippingDetailDb.setGuideNumber(shippingDetail.guideNumber());
            shippingDetailDb.setOrder(orderRepository.findById(shippingDetail.orderId()).orElseThrow());
            shippingDetailDb.setProduct(productRepository.findById(shippingDetail.productId()).orElseThrow());
            
            return shippingDetailMapper.toDTO(repository.save(shippingDetailDb));
        }).orElseThrow();
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ShippingDetailDTO> getByOrderId(Integer id) {
       var shippingDetailList = repository.findByOrderId(id);
       return shippingDetailList.stream().map( shippingDetailMapper::toDTO ).toList();
    }

    @Override
    public List<ShippingDetailDTO> getByConveyor(String conveyor) {
        var shippingDetailList = repository.findByConveyor(conveyor);
        return shippingDetailList.stream().map( shippingDetailMapper::toDTO ).toList();
    }

    @Override
    public List<ShippingDetailDTO> getByOrderStatus(String status) {
        var shippingDetailList = repository.findByOrderStatus(status);
        return shippingDetailList.stream().map( shippingDetailMapper::toDTO ).toList();
    }
    
}
