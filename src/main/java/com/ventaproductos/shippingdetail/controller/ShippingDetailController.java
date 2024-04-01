package com.ventaproductos.shippingdetail.controller;

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

import com.ventaproductos.shippingdetail.entity.ShippingDetailDTO;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTOSave;
import com.ventaproductos.shippingdetail.entity.ShippingDetailDTOUpdate;
import com.ventaproductos.shippingdetail.service.ShippingdetailServiceImp;

@RestController
@RequestMapping("/api/v1/shipping")
public class ShippingDetailController {
    private final ShippingdetailServiceImp shippingDetail;

    public ShippingDetailController(ShippingdetailServiceImp shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

    @GetMapping("/{id} ")
    public ResponseEntity<ShippingDetailDTO> get(@PathVariable("id") Integer id){
        return new ResponseEntity<>(shippingDetail.get(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ShippingDetailDTO>> getAll(){
        return new ResponseEntity<>(shippingDetail.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{orderId} ")
    public ResponseEntity<List<ShippingDetailDTO>> getByOrderId(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(shippingDetail.getByOrderId(orderId),HttpStatus.OK);
    }

    @GetMapping("/conveyor/{conveyor}")
    public ResponseEntity<List<ShippingDetailDTO>> getByConveyor(@PathVariable("conveyor") String conveyor){
        return new ResponseEntity<>(shippingDetail.getByConveyor(conveyor),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ShippingDetailDTO> create(@RequestBody ShippingDetailDTOSave dto) throws Exception{
        shippingDetail.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingDetailDTO> update(@PathVariable("id") Integer id, @RequestBody ShippingDetailDTOUpdate dto) {
        shippingDetail.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        shippingDetail.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
