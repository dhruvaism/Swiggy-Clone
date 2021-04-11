package com.swiggy.orderservice.dto;

import com.swiggy.orderservice.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private int id;
    private OrderStatus order_status;
    private String order_date;
    private Payment payment;
    private List<OrderItem> order_items;
    private CustomerAddress delivery_address;
    private Customer customer;
    private Restaurant restaurant;
    private  Bill bill;

}

