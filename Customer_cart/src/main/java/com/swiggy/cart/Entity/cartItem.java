package com.swiggy.cart.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Setter
@Getter
public class cartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartItemId;
    private int quantity=1;


    @ManyToOne
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @JsonBackReference
    private Food food;
}
