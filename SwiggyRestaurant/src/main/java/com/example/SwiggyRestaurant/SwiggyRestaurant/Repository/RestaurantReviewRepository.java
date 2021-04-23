package com.example.SwiggyRestaurant.SwiggyRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantReview;

public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long>{
	
	@Query("select rest from RestaurantReview where rest.customer = ?1")
	List<RestaurantReview> findAllByCustomerId(Long customerId);
	@Query("select rest from RestaurantReview where rest.restaurant = ?1")
	List<RestaurantReview> findAllByRestaurantId(Long restaurantId);
	@Query("select rest from RestaurantReview where rest.customer = ?1 and rest.restaurant = ?2")
	List<RestaurantReview> findAllByCustomerIdAndRestaurantId(Long customerId, Long restaurantId);
}
