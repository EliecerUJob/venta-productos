package com.ventaproductos.TestRepositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ventaproductos.AbstractIntegrationTest;
import com.ventaproductos.client.entity.ClientEntity;
import com.ventaproductos.client.repository.ClientRepository;
import com.ventaproductos.order.entity.OrderEntity;
import com.ventaproductos.order.entity.OrderStatusEnum;
import com.ventaproductos.order.repository.OrderRepository;
import com.ventaproductos.orderitem.entity.OrderItemEntity;
import com.ventaproductos.orderitem.repository.OrderItemRepository;
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.repository.ProductRepository;

public class TestOrderRepository extends AbstractIntegrationTest{
    
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public TestOrderRepository(OrderRepository orderRepository, ClientRepository clientRepository,
            OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @SuppressWarnings("null")
    List<OrderEntity> init(){

        ClientEntity client = ClientEntity.builder()
                    .id(1)
                    .name("Sergio A")
                    .email("salmazo@gmail.com")
                    .address("Parques de bolivar")
                    .build();
        
        clientRepository.save(client);

        ProductEntity product = ProductEntity.builder()
                    .name("Coca-Cola")
                    .price(1200.0)
                    .stock(12)
                    .build();
        productRepository.save(product);

        OrderEntity order = OrderEntity.builder()
                    .dateOrder(LocalDate.now())
                    .status(OrderStatusEnum.valueOf("PENDING"))
                    .client(client)
                    .build();
        orderRepository.save(order);

        OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order(order)
                    .product(product)
                    .quantity(1)
                    .unitPrice(1200.0)
                    .build();
        orderItemRepository.save(orderItem);
        
        return List.of(order);
    }

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    void givenOrder_whenFindByDateOrderBetween_thenReturnOrder(){
        //Given
        init();
        //When
        List<OrderEntity> orders = orderRepository.findByDateOrderBetween(LocalDate.of(2010, 01, 01), LocalDate.of(2025, 01, 1));
        //Then
        assertEquals(1, orders.size());
    }

    @Test
    void givenOrder_whenFindByDateOrderBetween_thenReturnEmpty(){
        //Given
        init();
        //When
        List<OrderEntity> orders = orderRepository.findByDateOrderBetween(LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 1));
        //Then
        assertEquals(0, orders.size());
    }

    @Test
    void givenOrder_whenFindByClientIdAndStatus_thenReturnOrder(){
        //Given
        init();
        //When
        List<OrderEntity> orders = orderRepository.findByClientIdAndStatus(1, OrderStatusEnum.valueOf("PENDING"));
        //Then
        assertEquals(1, orders.size());
    }

    @Test
    void givenOrder_whenFindByClientIdAndStatus_thenReturnEmpty(){
        //Given
        init();
        //When
        List<OrderEntity> orders = orderRepository.findByClientIdAndStatus(1, OrderStatusEnum.valueOf("SENT"));
        //Then
        assertEquals(0, orders.size());
    }

    // @Test
    // void givenOrder_whenRecuperateOrderWithItemsByCustomer_thenReturnOrder(){
    //     //Given
    //     var listClient = init();
    //     Integer id = listClient.get(0).getClient().getId();
    //     //When
    //     List<Object[]> orders = orderRepository.recuperateOrderWithItemsByCustomer(id);
    //     //Then
    //     assertEquals(1, orders.size());
    // }

}
