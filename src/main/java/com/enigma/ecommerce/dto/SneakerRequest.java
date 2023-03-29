package com.enigma.ecommerce.dto;

import com.enigma.ecommerce.model.Brand;
import com.enigma.ecommerce.utils.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SneakerRequest {
    @NotBlank(message = "Name Required")
    private String name;
    @NotBlank(message = "Brand Required")
    private Brand brand;
    @NotBlank(message = "Size Required")
    private Size size;
    @NotBlank(message = "Price Required")
    private Integer price;

}
