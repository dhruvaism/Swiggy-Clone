package com.swiggy.orderservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

    private int id;
    private String house_no;
    private String street_name;
    private String city;
    private String state;


}
