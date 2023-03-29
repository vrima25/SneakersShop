package com.enigma.ecommerce.dto;

import com.enigma.ecommerce.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class ReportResponse {
    private List<Order> orderList;
    private Integer grandTotal;

}
