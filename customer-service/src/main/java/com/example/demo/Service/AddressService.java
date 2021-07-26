package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Addressdto;
import com.example.demo.dto.StatusDto;
import com.example.demo.model.Address;
import com.example.demo.repositories.AddressRepository;

@Component
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	public StatusDto updateAddress(Addressdto addressdto,int id)
	{
		StatusDto statusdto=new StatusDto();
		Optional<Address>ob=addressRepository.findById(id);
        if(ob.isPresent())
        {
       	 addressRepository.save(addressdto.getOb());
       	 statusdto.setStatus(true);
       	 statusdto.setDescription("Successfully Updated");
        }
        else
        {
        	statusdto.setDescription("Id not present");
        	statusdto.setStatus(false);
        }
        return statusdto;
	}
}
