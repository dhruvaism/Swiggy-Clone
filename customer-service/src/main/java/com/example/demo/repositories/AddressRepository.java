package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
   
	@Query(value="Select * from address where p_id=id",nativeQuery = true)
	public List<Address> findByPid(int id);
}
