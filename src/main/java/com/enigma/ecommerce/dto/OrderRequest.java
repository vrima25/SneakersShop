package com.enigma.ecommerce.dto;

import com.enigma.ecommerce.model.Sneaker;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class OrderRequest {
    @NotBlank(message = "CustomerRequired")
    private String name;

    @NotBlank(message = "Address Required")
    private String address;

    private String orderDate;
    @NotBlank(message = "Sneaker Required")
    private Sneaker sneaker;
    @NotBlank(message = "Quantity Required")
    private int qty;
}
