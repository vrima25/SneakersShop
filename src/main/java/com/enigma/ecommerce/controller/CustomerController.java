package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.dto.response.SuccessResponse;
import com.enigma.ecommerce.model.Customer;
import com.enigma.ecommerce.repository.CustomerRepo;
import com.enigma.ecommerce.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll(){
        Iterable<Customer> customers = service.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Iterable<Customer>>("SUCCESS", customers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("DELETED", null));
    }

}
