package com.masai.app.swiggy_delivery.repository;

import com.masai.app.swiggy_delivery.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Integer> {

}
