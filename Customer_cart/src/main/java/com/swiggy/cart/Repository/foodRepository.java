package com.swiggy.cart.Repository;

import com.swiggy.cart.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface foodRepository extends JpaRepository<Food, Long> {
}
