package com.swiggy.cart.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long resId;

    private String resName;

    @OneToMany(mappedBy = "res",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Food> foodlist= new ArrayList<>();

    public void addFood(Food food)
    {
        this.foodlist.add(food);
    }

    public void removeFood(Food food)
    {
        this.foodlist.remove(food);
    }

}
