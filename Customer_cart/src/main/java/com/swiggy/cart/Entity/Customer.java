package com.swiggy.cart.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    private int id;
    private String name;
}
