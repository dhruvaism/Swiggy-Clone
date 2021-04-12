package com.example.SwiggyRestaurant.SwiggyRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantAddress;

@Repository
public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, String>{

	@Query("SELECT restAdd.id from RestaurantAddress restAdd where restAdd.city = city")
	List<String> findAllByCity(@Param("city") String city);

}
