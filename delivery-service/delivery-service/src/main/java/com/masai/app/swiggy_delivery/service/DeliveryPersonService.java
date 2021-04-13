package com.masai.app.swiggy_delivery.service;

import com.masai.app.swiggy_delivery.entity.DeliveryPerson;
import com.masai.app.swiggy_delivery.repository.DeliveryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPersonService {

    @Autowired
    DeliveryPersonRepository deliverypersonRepository;

    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliverypersonRepository.findAll();
    }

    public DeliveryPerson addDeliveryPerson(DeliveryPerson delivery_person) {
        return deliverypersonRepository.save(delivery_person);
    }

    public String updateDeliveryPerson(DeliveryPerson delivery_person) {
        Optional<DeliveryPerson> delivery_person1=deliverypersonRepository.findById(delivery_person.getId());
        if(delivery_person1.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person.getId()+"\n Update Failed";
        }
        delivery_person1.get().setId(delivery_person.getId());
        delivery_person1.get().setName((delivery_person.getName()));
        delivery_person1.get().setPhone_no((delivery_person.getPhone_no()));
        deliverypersonRepository.save(delivery_person1.get());
        return "Information Updated Successfully";
    }

    public DeliveryPerson findAvailableDeliverPerson(){
        List<DeliveryPerson> deliveryPeople=deliverypersonRepository.findAll();
        for (DeliveryPerson deliveryPerson:deliveryPeople) {
            if(deliveryPerson.isAvailable())
                return deliveryPerson;
        }
        return null;
    }

    public String deleteDeliveryPerson(int delivery_person_id) {
        Optional<DeliveryPerson> delivery_person=deliverypersonRepository.findById(delivery_person_id);
        if(delivery_person.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person_id+"\n Deletion Failed";
        }
        deliverypersonRepository.deleteById(delivery_person_id);
        return "Successfully Deleted Delivery Person - ID "+delivery_person_id;
    }

    public String updateDeliverPersonStatus(int delivery_person_id, Boolean isAvailable) {
        Optional<DeliveryPerson> delivery_person=deliverypersonRepository.findById(delivery_person_id);
        if(delivery_person.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person_id;
        }
        delivery_person.get().setAvailable(isAvailable);
        deliverypersonRepository.save(delivery_person.get());
        return "Successfully updated status of Delivery Person - ID "+delivery_person_id+" to "+isAvailable;
    }

    public String updateDeliveryPersonRating(int delivery_person_id, double rating) {
        Optional<DeliveryPerson> delivery_person=deliverypersonRepository.findById(delivery_person_id);
        if(delivery_person.isEmpty()){
            return "No Delivery Person with the ID "+delivery_person_id;
        }
        int originalRatingCount=delivery_person.get().getRating_count();
        double original_rating=delivery_person.get().getRating();
        int newRatingCount=originalRatingCount+1;
        double new_rating=((original_rating*originalRatingCount)+rating)/newRatingCount;
        delivery_person.get().setRating_count(newRatingCount);
        delivery_person.get().setRating(new_rating);
        deliverypersonRepository.save(delivery_person.get());
        return "Successfully Updated Rating for Delivery Person - ID "+delivery_person_id;
    }
}
