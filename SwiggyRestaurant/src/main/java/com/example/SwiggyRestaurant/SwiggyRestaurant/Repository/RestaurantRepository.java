package com.example.SwiggyRestaurant.SwiggyRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String>{
	
	@Query("FROM Restaurant rest where rest.restaurantName = restaurantName")
	List<Restaurant> findAllByRestaurantName(@Param("restaurantName") String restaurantName);
	
}
