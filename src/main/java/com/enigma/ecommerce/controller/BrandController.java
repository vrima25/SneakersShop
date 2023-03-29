package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.model.Brand;
import com.enigma.ecommerce.dto.BrandRequest;
import com.enigma.ecommerce.dto.response.SuccessResponse;
import com.enigma.ecommerce.service.BrandService;
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
@RequestMapping("/brands")
public class BrandController {
    private final BrandService service;
    @Autowired
    private ModelMapper mapper;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll(){
        Iterable<Brand> brands = service.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Iterable<Brand>>("SUCCESS", brands));
    }

    @PostMapping
    public ResponseEntity create(BrandRequest request){
        Brand brand = mapper.map(request, Brand.class);
        brand = service.create(brand);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<Brand>("CREATED", brand));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Optional<Brand> find = service.findById(id);
        Brand brand = mapper.map(find, Brand.class);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new SuccessResponse<Brand>("FOUNDED", brand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Brand>("DELETED", null));
    }

    @GetMapping(params = {"key", "value", "operator"})
    public ResponseEntity getAllBy(@RequestParam("key") String key, @RequestParam("value") String value,
                                   @RequestParam("operator") String operator){
        SearchCriteria searchCriteria = new SearchCriteria(key,value, Operator.valueOf(operator));
        List<Brand> brands = service.listBy(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Brand>>("SUCCESS", brands));

    }
}