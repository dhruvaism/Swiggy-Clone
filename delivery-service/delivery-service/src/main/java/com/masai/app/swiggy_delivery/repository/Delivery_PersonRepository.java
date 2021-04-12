package com.masai.app.swiggy_delivery.repository;

import com.masai.app.swiggy_delivery.entity.Delivery_Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Delivery_PersonRepository extends JpaRepository<Delivery_Person,String> {

}
