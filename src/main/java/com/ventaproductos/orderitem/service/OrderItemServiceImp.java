package com.ventaproductos.orderitem.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.order.mapper.OrderMapper;
import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.orderitem.mapper.OrderItemMapper;
import com.ventaproductos.orderitem.repository.OrderItemRepository;
import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.mapper.ProductMapper;

@Service
public class OrderItemServiceImp implements OrderItemServiceInterface{

    private OrderItemRepository repository;
    private OrderItemMapper orderItemMapper;
    private ProductMapper productMapper;
    private OrderMapper orderMapper;

    public OrderItemServiceImp(
            OrderItemRepository repository, 
            OrderItemMapper orderItemMapper,
            ProductMapper productMapper,
            OrderMapper orderMapper
        ) {
        this.repository = repository;
        this.orderItemMapper = orderItemMapper;
        this.productMapper = productMapper;
        this.orderMapper = orderMapper;
    }

    @SuppressWarnings("null")
    @Override
    public OrderItemDTO create(OrderItemDTO oItem) {
        OrderItemEntity orderItemEntity = orderItemMapper.toEntity(oItem);
        return orderItemMapper.toDTO(repository.save(orderItemEntity));
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderItemDTO> get(Integer id) {
        Optional<OrderItemEntity> orderItemEntity = repository.findById(id);
        return Optional.of(orderItemMapper.toDTO(orderItemEntity.get()));
    }

    @Override
    public List<OrderItemDTO> getAll() {
        return orderItemMapper.tOrderItemDTOList(repository.findAll());
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderItemDTO> update(Integer id, OrderItemDTO oItem) {

        return repository.findById(id).map( oItemDb -> {
            oItemDb.setOrder( orderMapper.toEntity(oItem.getOrder()) );
            oItemDb.setProduct( productMapper.toEntity(oItem.getProduct()) );
            oItemDb.setQuantity( oItem.getQuantity() );
            oItemDb.setUnitPrice( oItem.getUnitPrice() );

            return orderItemMapper.toDTO(repository.save(oItemDb));
        });
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // @Override
    // public OrderItemEntity getByOrderById(OrderEntity order, Integer id) {
    //     return repository.findByOrderById(order, id);
    // }

    @Override
    public List<OrderItemDTO> findByProduct(ProductDTO product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        return orderItemMapper.tOrderItemDTOList(repository.findByProduct(productEntity));
    }
    
}
