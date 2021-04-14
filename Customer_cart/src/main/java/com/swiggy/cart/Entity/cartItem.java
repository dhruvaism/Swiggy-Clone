package com.swiggy.cart.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart= cart;
    }

    @ManyToMany
    @JoinTable(name = "item_food",
            joinColumns = @JoinColumn(name = "CART_ITEM_ID "),
            inverseJoinColumns = @JoinColumn(name = "FOOD_ID"))
        List<Food> food= new ArrayList<>();

    public void addFood(Food item){
        this.food.add(item);
    }

    public void removeFood(cartItem item){
        this.food.remove(item);
    }
}
