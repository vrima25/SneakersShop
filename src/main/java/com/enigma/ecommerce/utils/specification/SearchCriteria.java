package com.enigma.ecommerce.utils.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private String value;
    private Operator operator;
}
