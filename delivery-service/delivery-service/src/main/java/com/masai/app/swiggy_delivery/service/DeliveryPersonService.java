package com.masai.app.swiggy_delivery.service;

import com.masai.app.swiggy_delivery.dto.DeliveryPersonDto;
import com.masai.app.swiggy_delivery.dto.StatusDto;
import com.masai.app.swiggy_delivery.entity.DeliveryPerson;
import com.masai.app.swiggy_delivery.repository.DeliveryPersonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPersonService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    public List<DeliveryPersonDto> getAllDeliveryPersons() {
        List<DeliveryPerson> deliveryPeople=deliveryPersonRepository.findAll();
        List<DeliveryPersonDto> deliveryPersonDtos=modelMapper.map(deliveryPeople,new TypeToken<List<DeliveryPersonDto>>(){}.getType());
        return deliveryPersonDtos;
    }

    public DeliveryPersonDto addDeliveryPerson(DeliveryPerson delivery_person) {
        deliveryPersonRepository.save(delivery_person);
        DeliveryPersonDto deliveryPersonDto=new DeliveryPersonDto();
        modelMapper.map(delivery_person,deliveryPersonDto);
        return deliveryPersonDto;
    }

    public StatusDto updateDeliveryPerson(DeliveryPerson delivery_person) {
        Optional<DeliveryPerson> delivery_person1=deliveryPersonRepository.findById(delivery_person.getId());
        StatusDto statusDto =new StatusDto();
        if(delivery_person1.isEmpty()){
            statusDto.setStatus(false);
            statusDto.setDescription("No Delivery Person with the ID " + delivery_person.getId() + "\n found");
        }
        else {
            modelMapper.map(delivery_person,delivery_person1);
            deliveryPersonRepository.save(delivery_person1.get());
            statusDto.setStatus(true);
            statusDto.setDescription("Information Updated Successfully for Delivery Person with the ID " + delivery_person.getId());
        }
        return statusDto;
    }

    public DeliveryPerson findAvailableDeliverPerson(){
        List<DeliveryPerson> deliveryPeople=deliveryPersonRepository.findAll();
        for (DeliveryPerson deliveryPerson:deliveryPeople) {
            if(deliveryPerson.isAvailable())
                return deliveryPerson;
        }
        return null;
    }

    public StatusDto deleteDeliveryPerson(int delivery_person_id) {
        Optional<DeliveryPerson> delivery_person=deliveryPersonRepository.findById(delivery_person_id);
        StatusDto statusDto =new StatusDto();
        if(delivery_person.isEmpty()){
            statusDto.setStatus(false);
            statusDto.setDescription("No Delivery Person with the ID " + delivery_person_id + "\n found");
        }
        deliveryPersonRepository.deleteById(delivery_person_id);
        statusDto.setStatus(false);
        statusDto.setDescription("Successfully Deleted Delivery Person - ID "+delivery_person_id);
        return statusDto;
    }

    public StatusDto updateDeliverPersonStatus(int delivery_person_id, Boolean isAvailable) {
        Optional<DeliveryPerson> delivery_person=deliveryPersonRepository.findById(delivery_person_id);
        StatusDto statusDto =new StatusDto();
        if(delivery_person.isEmpty()){
            statusDto.setStatus(false);
            statusDto.setDescription("No Delivery Person with the ID " + delivery_person_id + "\n found");
        }
        delivery_person.get().setAvailable(isAvailable);
        deliveryPersonRepository.save(delivery_person.get());
        statusDto.setStatus(true);
        statusDto.setDescription("Successfully updated status of Delivery Person - ID "+delivery_person_id+" to "+isAvailable);
        return statusDto;
    }

    public StatusDto updateDeliveryPersonRating(int delivery_person_id, double rating) {
        Optional<DeliveryPerson> delivery_person=deliveryPersonRepository.findById(delivery_person_id);
        StatusDto statusDto =new StatusDto();
        if(delivery_person.isEmpty()){
            statusDto.setStatus(false);
            statusDto.setDescription("No Delivery Person with the ID " + delivery_person_id + "\n found");
        }
        else {
            int originalRatingCount = delivery_person.get().getRatingCount();
            double original_rating = delivery_person.get().getRating();
            int newRatingCount = originalRatingCount + 1;
            double new_rating = ((original_rating * originalRatingCount) + rating) / newRatingCount;
            int ratingTemp = (int) (new_rating * 10);
            new_rating = ratingTemp / 10.0;
            delivery_person.get().setRatingCount(newRatingCount);
            delivery_person.get().setRating(new_rating);
            deliveryPersonRepository.save(delivery_person.get());
            statusDto.setStatus(true);
            statusDto.setDescription("Successfully Updated Rating for Delivery Person - ID "+delivery_person_id);
        }
        return statusDto;
    }
}
