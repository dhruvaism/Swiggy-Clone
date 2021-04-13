package com.masai.app.swiggy_delivery.repository;

import com.masai.app.swiggy_delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
}
