package com.swiggy.cart.Dto;

import com.swiggy.cart.Entity.restaurant;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {
    private String name;
    private String meal_type;
    private double price;
    private double rating;
    private boolean availability;
    private String resName;
}
