package com.swiggy.orderservice.dto;

import com.swiggy.orderservice.entity.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutCustomer {

    public int id;
    public String name;
    public String email;
    public String phone;
    private List<CustomerAddress> address_list;

}
