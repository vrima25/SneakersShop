package com.enigma.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BrandRequest {
    @NotBlank(message = "Name Required")
    private String name;
}
