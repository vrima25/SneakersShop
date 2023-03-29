package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.dto.response.SuccessResponse;
import com.enigma.ecommerce.model.Order;
import com.enigma.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final OrderService service;

    public ReportController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{date}")
    public ResponseEntity getReport(@PathVariable String date){
        List<Order> orderList = service.findByOrderDate(date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Order>>("SUCCESS", orderList));
    }

}
