package com.enigma.ecommerce.interceptior;

import com.enigma.ecommerce.utils.validation.JwtUtils;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Interceptor implements HandlerInterceptor {
    private JwtUtils jwtUtils;

    public Interceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().contains("login") || request.getRequestURI().contains("register")){
            return true;
        }

        String token = request.getHeader("Authorization");
        if(token == null) throw new RuntimeException("Token Not Found");

        String[] bearerToken = token.split(" ");
        return jwtUtils.validateToken(bearerToken[1]);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("POST HANDLE");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("COMPLETE");
    }
}
