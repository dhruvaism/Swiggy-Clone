package com.masai.app.swiggy_delivery.service;

import com.masai.app.swiggy_delivery.entity.Delivery;
import com.masai.app.swiggy_delivery.entity.DeliveryPerson;
import com.masai.app.swiggy_delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryPersonService deliveryPersonService;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }


    public String updateDelivery(Delivery delivery) {
        Optional<Delivery> delivery1=deliveryRepository.findById(delivery.getId());
        if(delivery1.isEmpty()) {
            return "No Delivery with the ID " + delivery.getId() + "\n Update Failed";
        }
        delivery1.get().setDeliveryPerson(delivery.getDeliveryPerson());
        delivery1.get().setOrderId(delivery.getOrderId());
        delivery1.get().setDeliveryDateTime(delivery.getDeliveryDateTime());
        deliveryRepository.save(delivery1.get());
        return "Information Updated Successfully";
    }

    public String deleteDelivery(int deliveryId) {
        Optional<Delivery> delivery=deliveryRepository.findById(deliveryId);
        if(delivery.isEmpty()){
            return "No Delivery with the ID "+deliveryId+"\n Deletion Failed";
        }
        deliveryRepository.deleteById(deliveryId);
        return "Successfully Deleted Delivery with the ID "+deliveryId;
    }

    public Object assignDeliveryPerson(int id) {
        //Find the first Delivery Person who is available
        DeliveryPerson deliverPerson=deliveryPersonService.findAvailableDeliverPerson();
        //If a delivery person is available assign him to the order
        if(deliverPerson!=null) {
            //Set Delivery Person to not available as he is out on delivery
            deliverPerson.setAvailable(false);
            Delivery delivery = new Delivery();
            delivery.setDeliveryPerson(deliverPerson);
            delivery.setOrderId(id);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String deliveryDate = dateFormat.format(date);
            delivery.setDeliveryDateTime(deliveryDate);
            return deliveryRepository.save(delivery);
            /*Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY,1);
            return calendar.getTime();*/
        }
        //RETURN A PROPER MESSAGE
        return "No delivery person available";
    }
}
