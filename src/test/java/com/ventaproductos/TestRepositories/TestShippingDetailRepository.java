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
import com.ventaproductos.product.entity.ProductEntity;
import com.ventaproductos.product.repository.ProductRepository;
import com.ventaproductos.shippingdetail.entity.ShippingDetailEntity;
import com.ventaproductos.shippingdetail.repository.ShippingDetailRepository;

public class TestShippingDetailRepository extends AbstractIntegrationTest{

    private final ShippingDetailRepository shippingDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    
    @Autowired
    public TestShippingDetailRepository(ShippingDetailRepository shippingDetailRepository,
            ProductRepository productRepository, OrderRepository orderRepository, ClientRepository clientRepository) {
        this.shippingDetailRepository = shippingDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @BeforeEach
    void setup(){
        clientRepository.deleteAll();
        shippingDetailRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }

    void init(){

        ClientEntity client = ClientEntity.builder()
                    .email("eliecer@gmail.com")
                    .address("123 Main St")
                    .name("Eliecer")
                    .build();
        clientRepository.save(client);

        ProductEntity product = ProductEntity.builder()
                        .name("Product 1")
                        .price(1200.0)
                        .stock(14)
                        .build();
        productRepository.save(product);

        OrderEntity order = OrderEntity.builder()
                       .client(client)
                       .dateOrder(LocalDate.now())
                       .status(OrderStatusEnum.valueOf("PENDING"))
                       .build();
        orderRepository.save(order);

        ShippingDetailEntity entity = ShippingDetailEntity.builder()
                        .order(order)
                        .conveyor("Transportadora 1")
                        .guideNumber(12345)
                        .build();
        shippingDetailRepository.save(entity); 
        
    }

    @Test
    void givenShippingDetail_whenFindByConveyor_thenReturnShippingDetailList(){
        //Given
        init();
        //When
        List<ShippingDetailEntity> shippingList = shippingDetailRepository.findByConveyor("Transportadora 1");
        //Then
        assertEquals(1, shippingList.size());
    }

    @Test
    void givenShippingDetail_whenFindByConveyor_thenReturnEmptyList(){
        //Given
        init();
        //When
        List<ShippingDetailEntity> shippingList = shippingDetailRepository.findByConveyor("Transportadora 2");
        //Then
        assertEquals(0, shippingList.size());
    }

    @Test
    void givenShippingDetail_whenFindByOrderId_thenReturnShippingDetail(){
        //Given
        init();
        //When
        var shippingList = shippingDetailRepository.findByOrderId(1);
        //Then
        assertEquals(1, shippingList.size());

    }

    @Test
    void givenShippingDetail_whenFindByOrderId_thenReturnEmptyList(){
        //Given
        init();
        //When
        var shippingList = shippingDetailRepository.findByOrderId(2);
        //Then
        assertEquals(0, shippingList.size());
    }

    @Test
    void givenShippingDetail_whenFindByOrderStatus_thenReturnShippingDetailList(){
        //Given
        init();
        //When
        List<ShippingDetailEntity> shippingList = shippingDetailRepository.findByOrderStatus(OrderStatusEnum.valueOf("PENDING"));
        //Then
        assertEquals(1, shippingList.size());
    }

    @Test
    void givenShippingDetail_whenFindByOrderStatus_thenReturnEmptyList(){
        //Given
        init();
        //When
        List<ShippingDetailEntity> shippingList = shippingDetailRepository.findByOrderStatus(OrderStatusEnum.valueOf("DELIVERED"));
        //Then
        assertEquals(0, shippingList.size());
    }
}
