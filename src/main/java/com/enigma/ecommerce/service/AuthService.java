package com.enigma.ecommerce.service;

import com.enigma.ecommerce.Exception.NotFoundException;
import com.enigma.ecommerce.dto.LoginRequest;
import com.enigma.ecommerce.dto.RegisterRequest;
import com.enigma.ecommerce.model.Admin;
import com.enigma.ecommerce.model.Auth;
import com.enigma.ecommerce.repository.AuthRepo;
import com.enigma.ecommerce.utils.validation.JwtUtils;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService implements IAuthService{
    private final AuthRepo repo;
    private final AdminService adminService;

    @Autowired
    private JwtUtils utils;
    @Autowired
    private ModelMapper mapper;

    public AuthService(AuthRepo repo, AdminService adminService) {
        this.repo = repo;
        this.adminService = adminService;
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        try {
            Auth auth = mapper.map(registerRequest, Auth.class);
            auth = repo.save(auth);

            Admin admin = mapper.map(registerRequest, Admin.class);
            admin.setAuth(auth);
            adminService.create(admin);

            String token = utils.generateToken(admin.getAuth().getEmail());
            System.out.println("token dibuat");
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException(e.getMessage());
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = repo.findById(loginRequest.getEmail());
            if (auth.isEmpty()) {
                throw new NotFoundException("USER NOT FOUND");
            }
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new RuntimeException("PASSWORD IS NOT MATCH");
            }

            String token = utils.generateToken(loginRequest.getEmail());
            return token;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
