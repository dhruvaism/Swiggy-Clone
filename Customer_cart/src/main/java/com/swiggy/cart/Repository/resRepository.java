package com.swiggy.cart.Repository;

import com.swiggy.cart.Entity.restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface resRepository extends JpaRepository<restaurant, Long> {
}
