package com.example.SwiggyRestaurant.SwiggyRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantAddress;

@Repository
public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, String>{

	@Query("SELECT restAdd.id FROM RestaurantAddress restAdd WHERE restAdd.city = ?1")
	List<String> findAllByCity(String city);

}
