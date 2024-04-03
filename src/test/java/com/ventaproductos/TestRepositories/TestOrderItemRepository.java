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

public class TestOrderItemRepository extends AbstractIntegrationTest{

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository  orderRepository;
    private final ClientRepository clientRepository;
    
    @Autowired
    public TestOrderItemRepository(OrderItemRepository orderItemRepository, ProductRepository productRepository,
            OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @BeforeEach
    void setUp(){
        orderItemRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @SuppressWarnings("null")
    void init(){

        ClientEntity client = ClientEntity.builder()
                    .name("Eliecer U")
                    .email("eliecer@gmail.com")
                    .address("La U")
                    .build();
        clientRepository.save(client);
        
        ProductEntity product1 = ProductEntity.builder()
                    .name("Product 1")
                    .price(3200.0)
                    .stock(15)
                    .build();
        
        ProductEntity product2 = ProductEntity.builder()
                    .name("Product 2")
                    .price(2200.0)
                    .stock(7)
                    .build();           

        productRepository.saveAll(List.of(product1, product2));
                    
        OrderEntity order = OrderEntity.builder()
                    .dateOrder(LocalDate.now())
                    .status(OrderStatusEnum.valueOf("PENDING"))
                    .build();
        orderRepository.save(order);
                    
        OrderItemEntity orderItem1 = OrderItemEntity.builder()
                    .product(product1)
                    .order(order)
                    .quantity(7)
                    .unitPrice(3200.0)
                    .build();
                    
        OrderItemEntity orderItem2 = OrderItemEntity.builder()
                    .product(product2)
                    .order(order)
                    .quantity(3)
                    .unitPrice(2200.0)
                    .build();
                    
        orderItemRepository.saveAll(List.of(orderItem1, orderItem2));
        orderItemRepository.flush();
    }       
                
    @Test
    void givenOrderItems_whenFindByProductId_thenReturnListOrderItems(){
        //Given
        init();
        //When
        List<OrderItemEntity> orderItems = orderItemRepository.findByProductId(productRepository.findAll().get(0).getId());
        //Then
        assertEquals(1, orderItems.size());
    }

    @Test
    void givenOrderItems_whenFindByProductId_thenReturnListEmpty(){
        //Given
        final Integer productId = 1;
        //When
        List<OrderItemEntity> orderItems = orderItemRepository.findByProductId(productId);
        //Then
        assertEquals(0, orderItems.size());
    }
                
    @Test
    void givenOrderItem_whenFindByOrderId_thenReturnListOrderItems(){
        //Given
        init();
        //When
        List<OrderItemEntity> orderItems = orderItemRepository.findByOrderId(orderRepository.findAll().get(0).getId());
        //Then
        assertEquals(2, orderItems.size());
    }

    @Test
    void givenOrderItem_whenFindByOrderId_thenReturnListEmpty(){
        //Given
        final Integer orderId = 1;
        //When
        List<OrderItemEntity> orderItems = orderItemRepository.findByOrderId(orderId);
        //Then
        assertEquals(0, orderItems.size());
    }
                
    @Test
    void givenOrderItem_whenFindByProductName_thenReturnListOrderItems(){
        //Given
        init();
        //When
        List<OrderItemEntity> orderItems = orderItemRepository.findByProductName(productRepository.findAll().get(0).getName());
        //Then
        assertEquals(1, orderItems.size());
    }
    
    @Test
    void givenOrderItem_whenSumTotalSalesByProduct_thenReturnTotalSales(){
        //Given
        init();
        final Integer orderItems = 1;
        //When
        Double totalSales = orderItemRepository.sumTotalSalesByProduct(orderItems);
        //Then
        assertEquals(22400.0, totalSales);
    }
    
}
