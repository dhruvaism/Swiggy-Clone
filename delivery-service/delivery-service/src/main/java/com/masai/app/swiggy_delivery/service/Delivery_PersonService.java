package com.masai.app.swiggy_delivery.service;

import com.masai.app.swiggy_delivery.entity.Delivery_Person;
import com.masai.app.swiggy_delivery.repository.Delivery_PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Delivery_PersonService {

    @Autowired
    Delivery_PersonRepository delivery_personRepository;

    public List<Delivery_Person> getAllDeliveryPersons() {
        return delivery_personRepository.findAll();
    }

    public Delivery_Person addDeliveryPerson(Delivery_Person delivery_person) {
        return delivery_personRepository.save(delivery_person);
    }

    public String updateDeliveryPerson(Delivery_Person delivery_person) {
        Optional<Delivery_Person> delivery_person1=delivery_personRepository.findById(delivery_person.getId());
        if(delivery_person1.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person.getId()+"\n Update Failed";
        }
        delivery_person1.get().setId(delivery_person.getId());
        delivery_person1.get().setName((delivery_person.getName()));
        delivery_person1.get().setPhone_no((delivery_person.getPhone_no()));
        delivery_personRepository.save(delivery_person1.get());
        return "Information Updated Successfully";
    }

    public String deleteDeliveryPerson(String delivery_person_id) {
        Optional<Delivery_Person> delivery_person=delivery_personRepository.findById(delivery_person_id);
        if(delivery_person.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person_id+"\n Deletion Failed";
        }
        delivery_personRepository.deleteById(delivery_person_id);
        return "Successfully Deleted Delivery Person - ID "+delivery_person_id;
    }

    public String updateDeliverPersonStatus(String delivery_person_id, Boolean isAvailable) {
        Optional<Delivery_Person> delivery_person=delivery_personRepository.findById(delivery_person_id);
        if(delivery_person.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person_id;
        }
        delivery_person.get().setAvailable(isAvailable);
        delivery_personRepository.save(delivery_person.get());
        return "Successfully updated status of Delivery Person - ID "+delivery_person_id+" to "+isAvailable;
    }

    public String updateDeliveryPersonRating(String delivery_person_id, double rating) {
        Optional<Delivery_Person> delivery_person=delivery_personRepository.findById(delivery_person_id);
        if(delivery_person.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person_id;
        }
        int originalRatingCount=delivery_person.get().getRating_count();
        double original_rating=delivery_person.get().getRating();
        int newRatingCount=originalRatingCount+1;
        double new_rating=((original_rating*originalRatingCount)+rating)/newRatingCount;
        delivery_person.get().setRating_count(newRatingCount);
        delivery_person.get().setRating(new_rating);
        delivery_personRepository.save(delivery_person.get());
        return "Successfully Updated Rating for Delivery Person - ID "+delivery_person_id;
    }
}
