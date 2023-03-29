package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.model.Customer;
import com.enigma.ecommerce.model.Order;
import com.enigma.ecommerce.dto.OrderRequest;
import com.enigma.ecommerce.dto.response.SuccessResponse;
import com.enigma.ecommerce.service.CustomerService;
import com.enigma.ecommerce.service.OrderService;
import com.enigma.ecommerce.utils.GenerateDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private GenerateDate generateDate;
    private final CustomerService customerService;
    private final OrderService orderService;

    public OrderController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity create(@RequestBody OrderRequest request){
        try{
            Customer customer = mapper.map(request, Customer.class);
            Order order = mapper.map(request, Order.class);

            Iterable<Order> orders = orderService.findAll();

            if(!orders.iterator().hasNext()){
                customer = customerService.create(customer);
            } else {
                for (Order find : orders) {
                    if (find.getCustomer().getName().equals(customer.getName())) {
                        customer = find.getCustomer();
                    }
                }
                customer = customerService.create(customer);
            }
            order.setOrderDate(GenerateDate.generate(request.getOrderDate()));
            order.setCustomer(customer);
            orderService.create(order);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponse<Order>("CREATED", order));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity getAll(){
        Iterable<Order> orders = orderService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Iterable<Order>>("SUCCESS", orders));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("DELETED", null));
    }
}
