package com.swiggy.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private int id;
    private double total_price;
    private double discount;
    private double delivery_charge;
    private List<CartItem> cart_items;


}
