package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.dto.LoginRequest;
import com.enigma.ecommerce.dto.RegisterRequest;
import com.enigma.ecommerce.dto.response.SuccessResponse;
import com.enigma.ecommerce.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService service;

    public AuthController(IAuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest){
        String token = service.register(registerRequest);
        System.out.println("Ini jalan");
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("REGISTRATION SUCCESS", token));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){
        String token = service.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("LOGIN SUCCESS", token));
    }
}
