package com.swiggy.cart.Repository;

import com.swiggy.cart.Entity.cartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartItemrepository extends JpaRepository<cartItem, Long> {
}
