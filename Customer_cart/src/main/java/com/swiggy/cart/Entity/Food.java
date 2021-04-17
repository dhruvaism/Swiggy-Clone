package com.swiggy.cart.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @ManyToOne
    private restaurant res;

    @OneToMany(mappedBy = "food",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<cartItem> cartItemList = new ArrayList<>();

    public void addCartItem(cartItem item)
    {
        this.cartItemList.add(item);
    }

    public void removeCartItem(cartItem item)
    {
        this.cartItemList.remove(item);
    }

}
