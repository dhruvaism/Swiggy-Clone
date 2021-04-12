package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RestaurantDto {
	
	Restaurant restaurant;
}
