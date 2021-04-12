package com.example.SwiggyRestaurant.SwiggyRestaurant.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantAddress;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantAddressRepository;

@Service
public class RestaurantAddressService {
	
	@Autowired
	RestaurantAddressRepository restaurantAddressRepository;
	
	public List<RestaurantAddress> getAllAddress() {
		List<RestaurantAddress> allAddress = restaurantAddressRepository.findAll();
		return allAddress;
	}
	
	public RestaurantAddress getAddress(String addressId) {
		Optional<RestaurantAddress> restaurantAddress = restaurantAddressRepository.findById(addressId);
		if (restaurantAddress.isEmpty()) {
			return null;
		}
		return restaurantAddress.get();
	}
	
	public String addAddress(RestaurantAddress restaurantAddress) {
		try {
			restaurantAddressRepository.save(restaurantAddress);
			return "Saved Successfully";
		}
		catch(Exception ex) {
			return "Incomplete Address";
		}
	}

	public String updateAddress(RestaurantAddress restaurantAddress) {
		Optional<RestaurantAddress> restaurantAddress1 = 
				restaurantAddressRepository.findById(restaurantAddress.getRestaurantAddressId());
		
		if (restaurantAddress1.isEmpty()) {
			return "Something went wrong :(";
		}
		
		ModelMapper modelMapper = new ModelMapper();
		
		RestaurantAddress restaurantAddressRef = restaurantAddress1.get();
		restaurantAddressRef = modelMapper.map(restaurantAddress, RestaurantAddress.class);
		
		restaurantAddressRepository.save(restaurantAddressRef);
		
		return "Successfully updated";
	}

	public String deleteAddress(String restaurantAddressId) {
		Optional<RestaurantAddress> restaurantAddress1 = 
				restaurantAddressRepository.findById(restaurantAddressId);
		
		if (restaurantAddress1.isEmpty()) {
			return "Something went wrong :(";
		}
		RestaurantAddress restaurantAddress = restaurantAddress1.get();
		restaurantAddressRepository.delete(restaurantAddress);
		return "Deleted Successfully";
	}

}
