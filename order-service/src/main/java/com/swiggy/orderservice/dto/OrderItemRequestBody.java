package com.swiggy.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequestBody {
    private String food_name;
    private int quantity;
    private double price;
    private int food_id;



}
