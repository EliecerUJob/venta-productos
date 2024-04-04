package com.ventaproductos.orderitem.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.mapper.OrderMapper;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.orderitem.entity.OrderItemDTOSave;
import com.ventaproductos.orderitem.entity.OrderItemDTOUpdate;
import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.orderitem.mapper.OrderItemMapper;
import com.ventaproductos.orderitem.repository.OrderItemRepository;
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.mapper.ProductMapper;
import com.ventaproductos.product.repository.ProductRepository;

@Service
public class OrderItemServiceImp implements OrderItemServiceInterface{

    private OrderItemRepository orderItemRepository;

    private OrderItemMapper orderItemMapper;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public OrderItemServiceImp(
            OrderItemRepository orderItemRepository, 
            OrderItemMapper orderItemMapper,
            ProductMapper productMapper,
            OrderMapper orderMapper,
            ProductRepository productRepository,
            OrderRepository orderRepository
        ) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderItemDTO create(OrderItemDTOSave oItem) {
        ProductEntity productEntity = productRepository.findById(oItem.productId()).orElseThrow();
        OrderEntity orderEntity = orderRepository.findById(oItem.orderId()).orElseThrow();

        OrderItemDTOSave orderItemDTO = OrderItemDTOSave.builder()
                        .product(productEntity)
                        .order(orderEntity)
                        .orderId(oItem.orderId())
                        .productId(oItem.productId())
                        .quantity(oItem.quantity())
                        .unitPrice(oItem.unitPrice())
                        .build();
        
        return orderItemMapper.toDTO(orderItemRepository.save(orderItemMapper.toEntity(orderItemDTO)));
        
    }

    @Override
    public OrderItemDTO get(Integer id) {
        OrderItemEntity orderItemEntity = orderItemRepository.findById(id).orElseThrow();
        return orderItemMapper.toDTO(orderItemEntity);
    }

    @Override
    public List<OrderItemDTO> getAll() {
        return orderItemRepository.findAll().stream().map(orderItemMapper::toDTO).toList();
    }

    @Override
    public OrderItemDTO update(Integer id, OrderItemDTOUpdate oItem) {

        return orderItemRepository.findById(id).map( oItemDb -> {
            oItemDb.setOrder( orderRepository.findById(oItem.orderId()).orElseThrow() );
            oItemDb.setProduct( productRepository.findById(id).orElseThrow() );
            oItemDb.setQuantity( oItem.quantity() );
            oItemDb.setUnitPrice( oItem.unitPrice() );

            return orderItemMapper.toDTO(orderItemRepository.save(oItemDb));
        }).orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public List<OrderItemDTO> getOrderItemsdByProductId(Integer productId) {
        return orderItemRepository.findByProductId(productId).stream().map(orderItemMapper::toDTO).toList();
        
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(Integer id) {
        return orderItemRepository.findByOrderId(id).stream().map(orderItemMapper::toDTO).toList();

    }

    @Override
    public Double sumTotalSalesByProduct(Integer productId) {
        return orderItemRepository.sumTotalSalesByProduct(productId);
    }

    
    
}
