package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.dto.ReportResponse;
import com.enigma.ecommerce.dto.response.SuccessResponse;
import com.enigma.ecommerce.model.Order;
import com.enigma.ecommerce.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ModelMapper mapper;
    private final OrderService service;

    public ReportController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{date}")
    public ResponseEntity getReport(@PathVariable String date){
        List<Order> orderList = service.findByOrderDate(date);
        Integer grandTotal = 0 ;

        for(Order find : orderList){
            grandTotal = grandTotal + (find.getSneaker().getPrice() * find.getQty());
        }
         ReportResponse response = new ReportResponse();
        response.setOrderList(orderList);
        response.setGrandTotal(grandTotal);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<ReportResponse>("SUCCESS", response));
    }

}
