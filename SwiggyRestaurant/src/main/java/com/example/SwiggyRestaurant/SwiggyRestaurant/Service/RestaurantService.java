package com.example.SwiggyRestaurant.SwiggyRestaurant.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantAddressDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantAddress;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantAddressRepository;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantAddressRepository restaurantAddressRepository;

	@Autowired
	ModelMapper modelMapper;
	
	private List<RestaurantDto> convertRestaurantToDto(List<Restaurant> restaurants) {
		
		List<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>();
		
		for (Restaurant restaurant : restaurants) {
			restaurantDtos.add(modelMapper.map(restaurant, RestaurantDto.class));
		}
			
		return restaurantDtos;
	
	}
	
	
	private Restaurant convertDtoToRestaurant(RestaurantDto restaurantDto) {
	
		return modelMapper.map(restaurantDto, Restaurant.class);
	
	}
	
	
	private RestaurantDto convertRestaurantToDto(Restaurant restaurant) {
		
		return modelMapper.map(restaurant, RestaurantDto.class);
	
	}
	
	
	public ResponseEntity<?> getAllRestaurants() {
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		return new ResponseEntity<List<RestaurantDto>>(convertRestaurantToDto(restaurants), HttpStatus.ACCEPTED);
	
	}
	
	
	public ResponseEntity<?> getRestaurant(Long restaurantId) {
		
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		
		if (restaurant.isEmpty()) {
			return new ResponseEntity<String>("No restaurant is associated with this id", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<RestaurantDto>(convertRestaurantToDto(restaurant.get()), HttpStatus.FOUND );
	
	}
	
	
	public ResponseEntity<?> addRestaurant(RestaurantDto restaurantDto) {
		
		Restaurant restaurant = convertDtoToRestaurant(restaurantDto);
		restaurant.getRestaurantAddress().setRestaurant(restaurant);
		
		try {
			
			Restaurant savedRes = restaurantRepository.save(restaurant);
			return new ResponseEntity<RestaurantDto>(convertRestaurantToDto(savedRes),
					HttpStatus.CREATED
					);
		
		}
		catch (Exception e) {
		
			return new ResponseEntity<String>("This needs to worked for different exception",
					HttpStatus.PARTIAL_CONTENT);
		
		}
	
	}

	
	public ResponseEntity<?> updateRestaurant(RestaurantDto restaurant) {;

		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurant.getRestaurantId());
		
		if (restaurant1.isEmpty()) {
	
			return new ResponseEntity<String>("Who are you?", HttpStatus.NOT_FOUND);
		
		}
		
		Restaurant restaurantRef = restaurant1.get();
		modelMapper.map(restaurant, restaurantRef);
		
		restaurantRepository.save(restaurantRef);
		
		return new ResponseEntity<RestaurantDto>(restaurant, HttpStatus.ACCEPTED);	
		
	}

	
	public ResponseEntity<?> deleteRestaurant(Long restaurantId) {

		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurantId);
		
		if (restaurant1.isEmpty()) {
		
			return new ResponseEntity<String>("This ID has not been used", HttpStatus.NOT_FOUND);
		
		}
		
		restaurantAddressRepository.delete(restaurant1.get().getRestaurantAddress());
		
		restaurantRepository.delete(restaurant1.get());
		
		return new ResponseEntity<String>("Sorry, we couldn't help with our services", HttpStatus.ACCEPTED);
	
	}

	public ResponseEntity<?> searchRestaurantByCity(String cityName) {

		List<Long> restaurantId = restaurantAddressRepository.findAllByCity(cityName);
		
		List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantId);
		
		return new ResponseEntity<List<RestaurantDto>>(convertRestaurantToDto(restaurants), HttpStatus.ACCEPTED);
	
	}
	
	
	public ResponseEntity<?> searchRestaurantByName(String restaurantName) {		

		List<Restaurant> restaurants = restaurantRepository.findByRestaurantName(restaurantName);
		
		return new ResponseEntity<List<RestaurantDto>>(convertRestaurantToDto(restaurants), HttpStatus.ACCEPTED);
	
	}
	
	
	public ResponseEntity<?> updateAddress(Long restaurantId, RestaurantAddressDto restaurantAdd) {
		Optional<RestaurantAddress> restaurant1 = restaurantAddressRepository.findById(restaurantId);
		
		if (restaurant1.isEmpty()) {
			return new ResponseEntity<String>("This ID has not been used", HttpStatus.NOT_FOUND);
		}
		
		RestaurantAddress restaurantRef = restaurant1.get();
		modelMapper.map(restaurantAdd, restaurantRef);
		
		restaurantAddressRepository.save(restaurantRef);
		
		return new ResponseEntity<RestaurantAddressDto>(restaurantAdd, HttpStatus.ACCEPTED);
	
	}
}