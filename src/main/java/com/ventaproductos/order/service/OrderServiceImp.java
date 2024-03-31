package com.ventaproductos.order.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import com.ventaproductos.client.entity.ClientDTO;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.mapper.ClientMapper;
import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.mapper.OrderMapper;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.orderitem.mapper.OrderItemMapper;
import com.ventaproductos.payment.mapper.PaymentMapper;
import com.ventaproductos.shippingdetail.mapper.ShippingDetailMapper;

@Service
public class OrderServiceImp implements OrderServiceInterface{

    private OrderRepository repository;
    private OrderMapper orderMapper;
    private ClientMapper clientMapper;
    private OrderItemMapper orderItemMapper;
    private PaymentMapper paymentMapper;
    private ShippingDetailMapper shippingDetailMapper;
    

    public OrderServiceImp(OrderRepository repository, OrderMapper orderMapper, ClientMapper clientMapper,
            OrderItemMapper orderItemMapper, PaymentMapper paymentMapper, ShippingDetailMapper shippingDetailMapper) {
        this.repository = repository;
        this.orderMapper = orderMapper;
        this.clientMapper = clientMapper;
        this.orderItemMapper = orderItemMapper;
        this.paymentMapper = paymentMapper;
        this.shippingDetailMapper = shippingDetailMapper;
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderDTO> get(Integer id) {
        Optional<OrderEntity> orderEntity = repository.findById(id);
        if (orderEntity.isPresent()) {
            OrderEntity orderToDTO = orderEntity.get();
            return Optional.of(orderMapper.toDTO(orderToDTO));
        }
        return Optional.empty();
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        repository.findAll().stream().forEach( orderDb -> {
            orderDTOs.add(orderMapper.toDTO(orderDb));
        } );

        return orderDTOs;
        // return orderMapper.toOrderDTOList(repository.findAll());
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Optional<OrderDTO> update(Integer id, OrderDTO order) {
        Optional<OrderEntity> getOrder = repository.findById(id);

        if (getOrder.isPresent()) {

            OrderEntity orderDb = getOrder.get();

            orderDb.setDateOrder(order.getDateOrder());
            orderDb.setClient(clientMapper.toEntitySave(order.getClient()));
            orderDb.setOrderItems(orderItemMapper.toOrderItemEntityList(order.getOrderItems()));
            orderDb.setPayment(paymentMapper.toEntity(order.getPayment()));
            orderDb.setShippingDetail(shippingDetailMapper.toEntity(order.getShippingDetail()));

            orderDb.setStatus(order.getStatus());
        
            OrderEntity updateOrder = repository.save(orderDb);
            return Optional.of(orderMapper.toDTO(updateOrder));

        }

        return Optional.empty();
    }

    @SuppressWarnings("null")
    @Override
    public OrderDTO create(OrderDTO order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        return orderMapper.toDTO(repository.save(orderEntity));
    }

    @Override
    public List<OrderDTO> getByDateOrderBetween(LocalDate start, LocalDate end) {
        return orderMapper.toOrderDTOList(repository.findByDateOrderBetween(start, end));
    }

    @Override
    public List<OrderDTO> getByClientAndStatus(ClientDTO client, String status) {
        ClientEntity clientEntity = clientMapper.toEntity(client);
        return orderMapper.toOrderDTOList(repository.findByClientAndStatus(clientEntity, status));
    }

}
