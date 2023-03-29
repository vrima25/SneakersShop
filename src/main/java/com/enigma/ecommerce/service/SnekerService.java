package com.enigma.ecommerce.service;

import com.enigma.ecommerce.Exception.DuplicateException;
import com.enigma.ecommerce.Exception.NotFoundException;
import com.enigma.ecommerce.model.Sneaker;
import com.enigma.ecommerce.repository.SneakerRepo;
import com.enigma.ecommerce.utils.specification.SearchCriteria;
import com.enigma.ecommerce.utils.specification.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnekerService implements IService<Sneaker> {
    private final SneakerRepo repo;

    public SnekerService(SneakerRepo repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Sneaker> findAll() {
        try{
            Iterable<Sneaker> sneakers = repo.findAll();
            if(!sneakers.iterator().hasNext()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return sneakers;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sneaker create(Sneaker sneaker) {
        try{
            Iterable<Sneaker> sneakers = repo.findAll();
            for(Sneaker find : sneakers){
                if(find.getName().equals(sneaker.getName())){
                    throw new DuplicateException();
                }
            }
            return repo.save(sneaker);
        } catch (DuplicateException e){
            throw new DuplicateException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Sneaker> findById(Integer id) {
        try{
            Optional<Sneaker> sneaker = repo.findById(id);
            if(sneaker.isEmpty()){
                throw new NotFoundException();
            }
            return sneaker;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Optional<Sneaker> sneaker = repo.findById(id);
            if(sneaker.isEmpty()){
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
    public List<Sneaker> listBy(SearchCriteria searchCriteria) {
        try{
            Specification specification = new Spec<Sneaker>().findBy(searchCriteria);
            List<Sneaker> sneakers = repo.findAll(specification);
            if(sneakers.isEmpty()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return sneakers;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
