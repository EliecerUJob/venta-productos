package com.ventaproductos.orderitem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventaproductos.orderitem.entity.OrderItemDTO;
import com.ventaproductos.orderitem.entity.OrderItemDTOSave;
import com.ventaproductos.orderitem.entity.OrderItemDTOUpdate;
import com.ventaproductos.orderitem.service.OrderItemServiceImp;

@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {
    private final OrderItemServiceImp orderDetail;

    public OrderItemController(OrderItemServiceImp orderDetail){
        this.orderDetail = orderDetail;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> get(@PathVariable("id") Integer id){
        return new ResponseEntity<>(orderDetail.get(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OrderItemDTO>> getAll(){
        return new ResponseEntity<>(orderDetail.getAll(),HttpStatus.OK);
    }

    // OrderItemDTO getByOrderById(OrderEntity order, Integer id);

    // @GetMapping("/{productId}")
    // public ResponseEntity<List<OrderItemDTO>> findByProduct(@PathVariable("productId") Integer productId){
    //     return new ResponseEntity<>(orderDetail.findByProduct(productId),HttpStatus.OK);
    // }

    @PostMapping()
    public ResponseEntity<OrderItemDTO> create(@RequestBody OrderItemDTOSave dto) throws Exception {
        orderDetail.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> update(@PathVariable("id") Integer id, @RequestBody OrderItemDTOUpdate dto) {
       orderDetail.update(id, dto);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        orderDetail.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
