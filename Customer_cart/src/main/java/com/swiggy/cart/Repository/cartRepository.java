package com.swiggy.cart.Repository;

import com.swiggy.cart.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface cartRepository extends JpaRepository<Cart, Long> {

}
