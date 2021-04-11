package com.swiggy.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bill")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double item_total;

    private double discount;

    private double delivery_charge;

    private double order_total;




}
