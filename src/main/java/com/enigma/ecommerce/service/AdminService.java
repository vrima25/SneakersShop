package com.enigma.ecommerce.service;

import com.enigma.ecommerce.Exception.DuplicateException;
import com.enigma.ecommerce.Exception.NotFoundException;
import com.enigma.ecommerce.model.Admin;
import com.enigma.ecommerce.model.Brand;
import com.enigma.ecommerce.repository.AdminRepo;
import com.enigma.ecommerce.utils.specification.SearchCriteria;
import com.enigma.ecommerce.utils.specification.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IService<Admin> {
    private final AdminRepo repo;

    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Admin> findAll() {
        try{
            Iterable<Admin> admins = repo.findAll();
            if(!admins.iterator().hasNext()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return admins;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Admin create(Admin admin) {
        try{
            Iterable<Admin> admins = repo.findAll();
            for(Admin find : admins){
                if(find.getAuth().getEmail().equals(admin.getAuth().getEmail())){
//                    if(find.getAuth().isActive()){
                        throw new DuplicateException();
//                    }
                }
            }
            return repo.save(admin);
        } catch (DuplicateException e){
            throw new DuplicateException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Admin> findById(Integer id) {
        try{
            Optional<Admin> admin = repo.findById(id);
            if(admin.isEmpty()){
                throw new NotFoundException();
            }
            return admin;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            Optional<Admin> admin = repo.findById(id);
            if(admin.isEmpty()){
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
    public List<Admin> listBy(SearchCriteria searchCriteria) {
        try{
            Specification specification = new Spec<Admin>().findBy(searchCriteria);
            List<Admin> admins = repo.findAll(specification);
            if(admins.isEmpty()){
                throw new NotFoundException("DATABASE IS EMPTY");
            }
            return admins;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
