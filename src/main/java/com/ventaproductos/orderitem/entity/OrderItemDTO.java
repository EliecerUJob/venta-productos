package com.ventaproductos.orderitem.entity;

import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.product.entity.ProductDTO;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderItemDTO {
    
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal unitPrice;

    private OrderDTO order;
    private ProductDTO product;
    private List<ProductDTO> products;

}
