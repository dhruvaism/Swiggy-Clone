package com.swiggy.orderservice.repository;

import com.swiggy.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findAllByCustomer_Id(int customer_id); //attribute of order table

    List<Order> findAllByRestaurant_Id(int restaurant_id);

}
