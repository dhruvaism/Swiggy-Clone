package com.example.SwiggyRestaurant.SwiggyRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	@Query("select rest from Restaurant rest Where rest.restaurantName = ?1")
	List<Restaurant> findByRestaurantName(String restaurantName);
	
}
