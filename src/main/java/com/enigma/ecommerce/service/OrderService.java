package com.enigma.ecommerce.service;

import com.enigma.ecommerce.Exception.DuplicateException;
import com.enigma.ecommerce.Exception.NotFoundException;
import com.enigma.ecommerce.model.Order;
import com.enigma.ecommerce.model.Sneaker;
import com.enigma.ecommerce.repository.OrderRepo;
import com.enigma.ecommerce.utils.GenerateDate;
import com.enigma.ecommerce.utils.specification.SearchCriteria;
import com.enigma.ecommerce.utils.specification.Spec;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IService<Order>{
    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }


    @Override
    public Iterable<Order> findAll() {
        return repo.findAll();
//        try{
//            Iterable<Order> orders = repo.findAll();
//            if(!orders.iterator().hasNext()){
//                throw new NotFoundException("DATABASE IS EMPTY");
//            }
//            return orders;
//        } catch (NotFoundException e){
//            throw new NotFoundException(e.getMessage());
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public Order create(Order order) {
        return repo.save(order);
//        try {
//            Iterable<Order> orders = repo.findAll();
//            for (Order find : orders) {
//                if (find.getCustomer().getName().equals(order.getCustomer().getName())) {
//                    if(find.getSneaker().getId().equals(order.getSneaker().getId())){
//                        if(find.getOrderDate().equals(order.getOrderDate())){
//                            throw new DuplicateException();
//                        }
//                    }
//                }
//            }
//            return repo.save(order);
//        } catch (DuplicateException e) {
//            throw new DuplicateException(e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public Optional<Order> findById(Integer id) {
        try{
            Optional<Order> order = repo.findById(id);
            if(order.isEmpty()){
                throw new NotFoundException();
            }
            return order;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Optional<Order> order = repo.findById(id);
            if(order.isEmpty()){
                throw new NotFoundException();
            }
            repo.deleteById(id);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> listBy(SearchCriteria searchCriteria) {
        try{
            Specification specification = new Spec<Sneaker>().findBy(searchCriteria);
            List<Order> orders = repo.findAll(specification);
            if(orders.isEmpty()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return orders;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Order> findByOrderDate(String date){
        try{
            Iterable<Order> orders = repo.findAll();
            List<Order> data = new ArrayList<>();
            for (Order find : orders){
                if(find.getOrderDate().toString().contains(date)){
                    data.add(find);
                }
            }
            return data;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
