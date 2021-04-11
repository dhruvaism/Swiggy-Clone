package com.swiggy.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private int id;
    private int food_id;
    private String food_name;
    private double price;
    private int quantiy;
}
