package com.swiggy.orderservice.dto;

import com.swiggy.orderservice.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {


    private CheckoutCustomer customer;
    private Cart cart;

}
