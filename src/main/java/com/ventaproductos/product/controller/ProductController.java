package com.ventaproductos.product.controller;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.service.ProductServiceImp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServiceImp productService;
    
    public ProductController(ProductServiceImp productService) {
        this.productService = productService;
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductDTO>> getByStock() {
        return new ResponseEntity<List<ProductDTO>>(productService.getByStock(), HttpStatus.OK);
    }
    

}
