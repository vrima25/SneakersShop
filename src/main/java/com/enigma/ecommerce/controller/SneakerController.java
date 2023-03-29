package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.model.Sneaker;
import com.enigma.ecommerce.dto.SneakerRequest;
import com.enigma.ecommerce.dto.response.SuccessResponse;
import com.enigma.ecommerce.service.SnekerService;
import com.enigma.ecommerce.utils.specification.Operator;
import com.enigma.ecommerce.utils.specification.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sneakers")
public class SneakerController {
    private final SnekerService service;
    @Autowired
    private ModelMapper mapper;

    public SneakerController(SnekerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll(){
        Iterable<Sneaker> sneakers = service.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Iterable<Sneaker>>("SUCCESS", sneakers));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody SneakerRequest request) {
        Sneaker sneaker = mapper.map(request, Sneaker.class);
        sneaker = service.create(sneaker);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<Sneaker>("CREATED", sneaker));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Optional<Sneaker> find = service.findById(id);
        Sneaker sneaker = mapper.map(find, Sneaker.class);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new SuccessResponse<Sneaker>("FOUNDED", sneaker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Sneaker>("DELETED", null));
    }

    @GetMapping(params = {"key", "value", "operator"})
    public ResponseEntity getAllBy(@RequestParam("key") String key, @RequestParam("value") String value,
                                   @RequestParam("operator") String operator){
        SearchCriteria searchCriteria = new SearchCriteria(key,value, Operator.valueOf(operator));
        List<Sneaker> sneakers = service.listBy(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Sneaker>>("SUCCESS", sneakers));

    }

}
