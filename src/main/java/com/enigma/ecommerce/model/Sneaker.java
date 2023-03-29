package com.enigma.ecommerce.model;

import com.enigma.ecommerce.model.Brand;
import com.enigma.ecommerce.model.Order;
import com.enigma.ecommerce.utils.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sneakers")
@Getter @Setter
@NoArgsConstructor
public class Sneaker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private Size size;

    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> order;
}
