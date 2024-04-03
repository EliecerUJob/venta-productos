package com.ventaproductos.product.controller;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventaproductos.product.entity.ProductDTO;
import com.ventaproductos.product.entity.ProductDTOSave;
import com.ventaproductos.product.service.ProductServiceImp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServiceImp productService;
    
    public ProductController(ProductServiceImp productService) {
        this.productService = productService;
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductDTO>> getByStock(int quantity) {
        return new ResponseEntity<>(productService.getByStock(quantity), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> get(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> getByTerm(@RequestParam String searchTerm) {
        return new ResponseEntity<>(productService.getByNameContaining(searchTerm), HttpStatus.OK);
    }
    
    @GetMapping("/priceandstock")
    public ResponseEntity<List<ProductDTO>> getByPriceAndStock(@RequestParam double price, @RequestParam int stock) {
        return new ResponseEntity<>(productService.getByPriceAndStock(price, stock), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAll() {
        return new ResponseEntity<List<ProductDTO>>(productService.getAll(), HttpStatus.OK);
    }
    

    @PostMapping()
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTOSave dto) {
        productService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Integer id, @RequestBody ProductDTOSave dto) {
        productService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
