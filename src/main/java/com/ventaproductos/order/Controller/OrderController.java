package com.ventaproductos.order.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventaproductos.order.entity.OrderDTO;
import com.ventaproductos.order.service.OrderServiceImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderServiceImp orderService;
    
    public OrderController(OrderServiceImp orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderDTO>> get(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<OrderDTO>> getByDateOrderBetween(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return new ResponseEntity<>(orderService.getByDateOrderBetween(start, end), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<Optional<OrderDTO>> create(@RequestBody OrderDTO order) {
        orderService.create(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable("id") Integer id, @RequestBody OrderDTO Dto) {
        orderService.update(id, Dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
