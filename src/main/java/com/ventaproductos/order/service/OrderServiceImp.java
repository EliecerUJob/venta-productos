package com.ventaproductos.order.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.mapper.ClientMapper;
import com.ventaproductos.client.repository.ClientRepository;
import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderDTORecuperate;
import com.ventaproductos.order.entity.OrderDTOSave;
import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.entity.OrderStatusEnum;
import com.ventaproductos.order.mapper.OrderMapper;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.orderitem.mapper.OrderItemMapper;
import com.ventaproductos.payment.mapper.PaymentMapper;
import com.ventaproductos.shippingdetail.mapper.ShippingDetailMapper;

@Service
public class OrderServiceImp implements OrderServiceInterface{

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private OrderMapper orderMapper;

    public OrderServiceImp(
            OrderRepository orderRepository, 
            OrderMapper orderMapper, 
            ClientMapper clientMapper,
            OrderItemMapper orderItemMapper, 
            PaymentMapper paymentMapper, 
            ShippingDetailMapper shippingDetailMapper,
            ClientRepository clientRepository
        ) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.orderMapper = orderMapper;
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderDTO> get(Integer id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            OrderEntity orderToDTO = orderEntity.get();
            return Optional.of(orderMapper.toDTO(orderToDTO));
        }
        return Optional.empty();
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        orderRepository.findAll().stream().forEach( orderDb -> {
            orderDTOs.add(orderMapper.toDTO(orderDb));
        } );

        return orderDTOs;
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    @SuppressWarnings("null")
    public OrderDTO update(Integer id, OrderDTOSave order) {
        ClientEntity clientEntity = clientRepository.findById(order.clientId()).orElseThrow();
        
        return orderRepository.findById(id).map( orderDb -> {

            orderDb.setClient(clientEntity);
            orderDb.setDateOrder(order.dateOrder());
            orderDb.setStatus(order.status());
            
            orderRepository.save(orderDb);
            return orderMapper.toDTO(orderDb);

        }).orElseThrow();
    }

    @SuppressWarnings("null")
    @Override
    public OrderDTO create(OrderDTOSave order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        return orderMapper.toDTO(orderRepository.save(orderEntity));
    }

    @Override
    public List<OrderDTO> getByDateOrderBetween(LocalDate start, LocalDate end) {
        var orders = orderRepository.findByDateOrderBetween(start, end);
        return orders.stream().map(orderMapper::toDTO).toList();
    }

    @Override
    public List<OrderDTO> getOrderByClientIdAndStatus(Integer clientId, OrderStatusEnum status) {
        var orders = orderRepository.findByClientIdAndStatus(clientId, status);
        return orders.stream().map(orderMapper::toDTO).toList();
    }

    @Override
    public List<OrderDTORecuperate> getOrdersByRecuperateOrderWithItemsByCustomer(Integer clientId){
        var orders = orderRepository.recuperateOrderWithItemsByCustomer(clientId);
        return orders;
    }

}
