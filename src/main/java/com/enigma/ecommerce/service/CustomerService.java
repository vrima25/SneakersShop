package com.enigma.ecommerce.service;

import com.enigma.ecommerce.Exception.DuplicateException;
import com.enigma.ecommerce.Exception.NotFoundException;
import com.enigma.ecommerce.model.Brand;
import com.enigma.ecommerce.model.Customer;
import com.enigma.ecommerce.repository.CustomerRepo;
import com.enigma.ecommerce.utils.specification.SearchCriteria;
import com.enigma.ecommerce.utils.specification.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements IService<Customer> {
    private final CustomerRepo repo;

    public CustomerService(CustomerRepo repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Customer> findAll() {
        return repo.findAll();
//        try{
//            Iterable<Customer> customers = repo.findAll();
//            if(!customers.iterator().hasNext()){
//                throw new NotFoundException("DATABASE IS EMPTY");
//            }
//            return customers;
//        } catch (NotFoundException e){
//            throw new NotFoundException(e.getMessage());
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public Customer create(Customer customer)  {
        return repo.save(customer);
//        try{
//            Iterable<Customer> customers = repo.findAll();
//            for(Customer find : customers){
//                if(find.getName().equals(customer.getName())){
//                    throw new DuplicateException();
//                }
//            }
//            return repo.save(customer);
//        } catch (DuplicateException e){
//            throw new DuplicateException(e.getMessage());
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        try{
            Optional<Customer> customer = repo.findById(id);
            if(customer.isEmpty()){
                throw new NotFoundException();
            }
            return customer;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Optional<Customer> customer = repo.findById(id);
            if(customer.isEmpty()){
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
    public List<Customer> listBy(SearchCriteria searchCriteria) {
        try{
            Specification specification = new Spec<Brand>().findBy(searchCriteria);
            List<Customer> customers = repo.findAll((Sort) specification);
            if(customers.isEmpty()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return customers;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
