package com.swiggy.orderservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Restaurant {

    private int id;
    private String name;
    private Address address;

}
