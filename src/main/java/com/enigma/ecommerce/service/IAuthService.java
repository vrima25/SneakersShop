package com.enigma.ecommerce.service;

import com.enigma.ecommerce.dto.LoginRequest;
import com.enigma.ecommerce.dto.RegisterRequest;

public interface IAuthService {
    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}
