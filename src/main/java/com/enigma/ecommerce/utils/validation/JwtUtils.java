package com.enigma.ecommerce.utils.validation;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expire}")
    private Integer jwtExpiration;

    public String generateToken(String subject){
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration));
        return builder.compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e){
            throw new RuntimeException("Invalid JWT Token");
        } catch (ExpiredJwtException e){
            throw new RuntimeException("JWT Token Expired");
        } catch (UnsupportedJwtException e){
            throw new RuntimeException("JWT Token Unsupported");
        } catch (IllegalArgumentException e){
            throw new RuntimeException("JWT Token Invalid");
        } catch (SignatureException e){
            throw new RuntimeException("JWT Signature not Match");
        }
    }
}
