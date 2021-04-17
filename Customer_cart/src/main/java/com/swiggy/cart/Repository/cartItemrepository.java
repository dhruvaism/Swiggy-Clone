package com.swiggy.cart.Repository;

import com.swiggy.cart.Entity.cartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cartItemrepository extends JpaRepository<cartItem, Long> {
}
