package com.example.SwiggyRestaurant.SwiggyRestaurant.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantAddressRepository;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantAddressRepository restaurantAddressRepository;

	
	public Restaurant getRestaurant(String restaurantId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		if (restaurant.isEmpty()) {
			return null;
		}
		Restaurant restaurant1 = restaurant.get();
		return restaurant1;
	}
	
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants;
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		try {
			Restaurant restaurant1 = restaurantRepository.save(restaurant);
			return restaurant1;
		}
		catch(Exception ex) {
			return null;
		}
	}

	public String updateRestaurant(Restaurant restaurant) {
		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurant.getRestaurantId());
		
		if (restaurant1.isEmpty()) {
			return "Restaurant Not Found";
		}
		
		ModelMapper modelMapper = new ModelMapper();
		
		Restaurant restaurantRef = restaurant1.get();
		restaurantRef = modelMapper.map(restaurant, Restaurant.class);
		restaurantRepository.save(restaurantRef);
		return "Successfully Updated";
	}

	public String deleteRestaurant(String restaurantId) {
		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurantId);
		
		if (restaurant1.isEmpty()) {
			return "Invalid Input";
		}
		
		restaurantRepository.delete(restaurant1.get());
		return "Successfully Deleted";
	}

	public List<Restaurant> searchRestaurantByCity(String cityName) {
		List<String> restaurantId = restaurantAddressRepository.findAllByCity(cityName);
		List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantId);
		return restaurants;
	}
	
	public List<Restaurant> searchRestaurantByName(String restaurantName) {
		List<Restaurant> restaurants = restaurantRepository.findAllByRestaurantName(restaurantName);
		return restaurants;
	}
	
}
