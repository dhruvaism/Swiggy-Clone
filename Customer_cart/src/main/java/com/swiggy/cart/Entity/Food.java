package com.swiggy.cart.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long foodId;

    private String name;
    private String meal_type;
    private double price;
    private double rating;
    private boolean availability;
    private int rateCount;

    @ManyToMany(mappedBy = "food")
    @JsonBackReference
    List<cartItem> cart= new ArrayList<>();

    public void addCart(cartItem item){
        this.cart.add(item);
    }

    public void removeStudent(cartItem item){
        this.cart.remove(item);
    }

    @ManyToOne
    private restaurant res;
}
