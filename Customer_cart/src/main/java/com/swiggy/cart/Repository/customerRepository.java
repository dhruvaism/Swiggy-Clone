package com.swiggy.cart.Repository;

import com.swiggy.cart.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<Customer, Integer> {
}
