package com.enigma.ecommerce.service;

import com.enigma.ecommerce.Exception.DuplicateException;
import com.enigma.ecommerce.Exception.NotFoundException;
import com.enigma.ecommerce.model.Brand;
import com.enigma.ecommerce.repository.BrandRepo;
import com.enigma.ecommerce.utils.specification.SearchCriteria;
import com.enigma.ecommerce.utils.specification.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IService<Brand> {
    private final BrandRepo repo;

    public BrandService(BrandRepo repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Brand> findAll() {
        try{
            Iterable<Brand> brands = repo.findAll();
            if(!brands.iterator().hasNext()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return brands;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Brand create(Brand brand) {
        try{
            Iterable<Brand> brands = repo.findAll();
            for(Brand find : brands){
                if(find.getName().equals(brand.getName())){
                    throw new DuplicateException();
                }
            }
            return repo.save(brand);
        } catch (DuplicateException e){
            throw new DuplicateException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Brand> findById(Integer id) {
        try{
            Optional<Brand> brand = repo.findById(id);
            if(brand.isEmpty()){
                throw new NotFoundException();
            }
            return brand;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Optional<Brand> brand = repo.findById(id);
            if(brand.isEmpty()){
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
    public List<Brand> listBy(SearchCriteria searchCriteria) {
        try{
            Specification specification = new Spec<Brand>().findBy(searchCriteria);
            List<Brand> brands = repo.findAll(specification);
            if(brands.isEmpty()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return brands;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
