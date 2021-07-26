package com.example.SwiggyRestaurant.SwiggyRestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
