package com.swiggy.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    public int id;
    public String name;
    public String email;
    public String phone;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private CustomerAddress address;

}
