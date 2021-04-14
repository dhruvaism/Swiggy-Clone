package com.swiggy.cart.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    private double total_price;
    private double discount;
    private String suggestion="";


    @ToString.Exclude
    @OneToMany(mappedBy = "cart",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<cartItem> itemlist= new ArrayList<>();

    public void addItem(cartItem cItem)
    {
        this.itemlist.add(cItem);
    }

    public void removeItem(cartItem cItem)
    {
        this.itemlist.remove(cItem);
    }



}
