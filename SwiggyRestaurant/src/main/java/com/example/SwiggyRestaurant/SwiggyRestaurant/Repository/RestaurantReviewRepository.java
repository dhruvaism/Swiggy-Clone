package com.example.SwiggyRestaurant.SwiggyRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantReview;

public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long>{
	
	List<RestaurantReview> findByAndRestaurant_IdOrderByReviewCreated(Long id);
	
	List<RestaurantReview> findByAndCustomer_IdOrderByReviewCreated(Long id);
}
