package com.swiggy.orderservice.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestBody {
    private int restaurant_id;
    private int address_id;
    private int payment_id;
    private double item_total;
    private double discount;
    private double delivery_charge;
    private double order_total;

    private List<OrderItemRequestBody> order_items;


}





