package com.swiggy.orderservice.entity;

import com.swiggy.orderservice.entity.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    private int id;
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    RestaurantAddress address;

}
