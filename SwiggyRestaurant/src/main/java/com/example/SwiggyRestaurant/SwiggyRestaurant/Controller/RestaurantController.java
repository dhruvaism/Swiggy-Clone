package com.example.SwiggyRestaurant.SwiggyRestaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurants() { 
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		return restaurants;
	}
	
	@GetMapping("/restaurant/{id}")
	public Restaurant getRestaurant(@PathVariable("id") String restaurantId) {
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		return restaurant;
	}
	
	@GetMapping("/restaurant/search/city/{cityname}")
	public List<Restaurant> searchRestaurantByCity(@PathVariable("cityname") String cityName) {
		List<Restaurant> restaurants = restaurantService.searchRestaurantByCity(cityName);
		return restaurants;
	}
	
	@PostMapping("/restaurant")
	public Restaurant addRestaurant(@RequestBody RestaurantDto restaurant) {
		Restaurant restaurantCopy = restaurant.getRestaurant();
		return restaurantService.addRestaurant(restaurantCopy);
	}
	
	@PutMapping("/restaurant")
	public String updateRestaurant(@RequestBody Restaurant restaurant) {
		String message = restaurantService.updateRestaurant(restaurant);
		return message;
	}
	
	@DeleteMapping("/restaurant/{id}")
	public String deleteRestaurant(@PathVariable("id") String restaurantId) {
		String message = restaurantService.deleteRestaurant(restaurantId);
		return message;
	}
	
}
