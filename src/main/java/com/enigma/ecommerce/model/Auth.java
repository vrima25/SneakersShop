package com.enigma.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "auth")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Auth {
    @Id
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "is_active")
    private boolean isActive = true;

}