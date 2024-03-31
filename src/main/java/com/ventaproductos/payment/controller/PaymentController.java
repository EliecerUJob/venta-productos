package com.ventaproductos.payment.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ventaproductos.payment.entity.PaymentDTO;
import com.ventaproductos.payment.service.PaymentServiceImp;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentServiceImp paymentService;

    public PaymentController(PaymentServiceImp paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping()
    public ResponseEntity<List<PaymentDTO>> getAll(){
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PaymentDTO>> get(@PathVariable("id") Integer id){
        return new ResponseEntity<>(paymentService.get(id), HttpStatus.OK);
    }

    
    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDTO>> getByPaymentDateBetween(@RequestParam LocalDate start, @RequestParam LocalDate end){
        return new ResponseEntity<>(paymentService.getByPaymentDateBetween(start, end), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO dto) throws Exception {
        paymentService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable("id") Integer id, @RequestBody PaymentDTO dto) {
        paymentService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        paymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @GetMapping("/order/{orderId}")
    // public ResponseEntity

}
