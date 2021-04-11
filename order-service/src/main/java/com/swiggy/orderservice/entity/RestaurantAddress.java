package com.swiggy.orderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_address")
public class RestaurantAddress {

    @Id
    private int id;
    private String street_name;
    private String city;
    private String state;
    private String pincode;
    private String landmark;

}
