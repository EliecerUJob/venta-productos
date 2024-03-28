package com.ventaproductos.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderServiceInterface{

    
    private OrderRepository repository;
    
    public OrderServiceImp(OrderRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderEntity> get(Integer id) {
        return repository.findById(id);        
    }

    @Override
    public List<OrderEntity> getAll() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderEntity> update(Integer id, OrderEntity order) {
        return repository.findById(id).map( orderDb -> {
            orderDb.setClient(order.getClient());
            orderDb.setDateOrder(order.getDateOrder());
            orderDb.setOrderItems(order.getOrderItems());
            orderDb.setPayment(order.getPayment());
            orderDb.setShippingDetail(order.getShippingDetail());
            orderDb.setStatus(order.getStatus());

            return repository.save(orderDb);
        } );
    }

    @SuppressWarnings("null")
    @Override
    public OrderEntity create(OrderEntity order) {
        return repository.save(order);
    }

    @Override
    public List<OrderEntity> getByDateOrderBetween(LocalDate start, LocalDate end) {
        return repository.findByDateOrderBetween(start, end);
    }

    @Override
    public List<OrderEntity> getByClientAndStatus(ClientEntity client, String status) {
        return repository.findByClientAndStatus(client, status);
    }
    
}
