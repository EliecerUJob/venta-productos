package com.ventaproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	"/com.ventaproductos.client.mapper.ClientMapper",
	"com.ventaproductos.client.controller.ClientController",
	"/com.ventaproductos.order.mapper.OrderMapper",
    "/com.ventaproductos.orderitem.mapper.OrderItemMapper",
	"/com.ventaproductos.payment.mapper.PaymentMapper",
    "/com.ventaproductos.product.mapper.ProductMapper",
    "/com.ventaproductos.product.controller.ProductController",
	"/com.ventaproductos.shippingdetail.mapper.ShippingDetailMapper",
})
public class VentaproductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VentaproductosApplication.class, args);
	}

}
