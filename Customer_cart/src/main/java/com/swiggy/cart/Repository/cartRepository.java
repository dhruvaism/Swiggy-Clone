package com.swiggy.cart.Repository;

import com.swiggy.cart.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cartRepository extends JpaRepository<Cart, Long> {

}
