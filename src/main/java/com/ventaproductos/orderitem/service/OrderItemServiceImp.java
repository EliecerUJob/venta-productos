package com.ventaproductos.orderitem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.orderitem.repository.OrderItemRepository;
import com.ventaproductos.product.entity.ProductEntity;

@Service
public class OrderItemServiceImp implements OrderItemServiceInterface{

    private OrderItemRepository repository;

    public OrderItemServiceImp(OrderItemRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    @Override
    public OrderItemEntity create(OrderItemEntity oItem) {
        return repository.save(oItem);
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderItemEntity> get(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<OrderItemEntity> getAll() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<OrderItemEntity> update(Integer id, OrderItemEntity oItem) {
        return repository.findById(id).map( oItemDb -> {
            oItemDb.setOrder(oItem.getOrder());
            oItemDb.setProduct( oItem.getProduct() );
            oItemDb.setQuantity( oItem.getQuantity() );
            oItemDb.setUnitPrice( oItem.getUnitPrice() );

            return repository.save(oItemDb);
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
    public List<OrderItemEntity> findByProduct(ProductEntity product) {
        return repository.findByProduct(product);
    }
    
}
