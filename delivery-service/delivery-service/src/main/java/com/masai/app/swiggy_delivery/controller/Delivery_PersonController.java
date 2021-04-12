package com.masai.app.swiggy_delivery.controller;


import com.masai.app.swiggy_delivery.entity.Delivery_Person;
import com.masai.app.swiggy_delivery.service.Delivery_PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Delivery_PersonController {
    @Autowired
    Delivery_PersonService delivery_personService;

    //Admin
    @GetMapping("/allDeliveryPersons")
    public List<Delivery_Person> getAllDeliveryPersons(){
        return delivery_personService.getAllDeliveryPersons();
    }

    //Admin
    @PostMapping("/deliveryPerson/Add")
    public Delivery_Person addDeliveryPerson(@RequestBody Delivery_Person delivery_person){
        return delivery_personService.addDeliveryPerson(delivery_person);
    }

    //Admin & Delivery Person
    @PutMapping("/deliveryPerson/update")
    public String updateDeliveryPerson(@RequestBody Delivery_Person delivery_person){
        return delivery_personService.updateDeliveryPerson(delivery_person);
    }

    //Admin & Delivery Person
    @PutMapping("/deliveryPerson/{id}/update/status/{isAvailable}")
    public String updateDeliveryPersonRating(@PathVariable("id") String delivery_person_Id,@PathVariable("isAvailable") boolean isAvailable){
        return delivery_personService.updateDeliverPersonStatus(delivery_person_Id,isAvailable);
    }

    //Admin
    @DeleteMapping("/deliverPerson/delete/{id}")
    public String deleteDeliveryPerson(@PathVariable("id") String delivery_person_Id){
        return delivery_personService.deleteDeliveryPerson(delivery_person_Id);
    }


    //Customer
    @PutMapping("/deliveryPerson/{id}/update/rating/{rating}")
    public String updateDeliveryPersonRating(@PathVariable("id") String delivery_person_Id,@PathVariable("rating") double rating){
        return delivery_personService.updateDeliveryPersonRating(delivery_person_Id,rating);
    }

}
