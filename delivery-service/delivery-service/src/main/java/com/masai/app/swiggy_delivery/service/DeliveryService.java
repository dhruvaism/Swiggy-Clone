package com.masai.app.swiggy_delivery.service;

import com.masai.app.swiggy_delivery.dto.DeliveryDto;
import com.masai.app.swiggy_delivery.dto.StatusDto;
import com.masai.app.swiggy_delivery.entity.Delivery;
import com.masai.app.swiggy_delivery.entity.DeliveryPerson;
import com.masai.app.swiggy_delivery.repository.DeliveryRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DeliveryPersonService deliveryPersonService;

    public List<DeliveryDto> getAllDeliveries() {
        DeliveryDto deliveryDto=new DeliveryDto();
        List<DeliveryDto> deliveries=new ArrayList<>();
        for (Delivery delivery:deliveryRepository.findAll()) {
            modelMapper.map(delivery,deliveryDto);
            deliveries.add(deliveryDto);
        }
        return deliveries;
    }


    public StatusDto updateDelivery(Delivery delivery) {
        Optional<Delivery> delivery1=deliveryRepository.findById(delivery.getId());
        StatusDto statusDto =new StatusDto();
        if(delivery1.isEmpty()) {
            statusDto.setStatus(false);
            statusDto.setDescription("No Delivery with the ID " + delivery.getId() + "\n Update Failed");
        }
        else {
            statusDto.setStatus(true);
            statusDto.setDescription("Delivery Information Updated Successfully");

        /*delivery1.get().setDeliveryPerson(delivery.getDeliveryPerson());
        delivery1.get().setOrderId(delivery.getOrderId());
        delivery1.get().setDeliveryDateTime(delivery.getDeliveryDateTime());*/
            DeliveryDto deliveryDto = new DeliveryDto();
            modelMapper.map(delivery1, deliveryDto);
            deliveryRepository.save(delivery1.get());
        }

        return statusDto;
    }

    public StatusDto deleteDelivery(int deliveryId) {
        Optional<Delivery> delivery=deliveryRepository.findById(deliveryId);
        StatusDto statusDto =new StatusDto();
        if(delivery.isEmpty()){
            statusDto.setStatus(false);
            statusDto.setDescription("No Delivery with the ID " + deliveryId + "\n Deletion Failed");
        }
        else {
            statusDto.setStatus(true);
            statusDto.setDescription("Successfully Deleted Delivery with the ID " + deliveryId);
            deliveryRepository.deleteById(deliveryId);
        }
        return statusDto;
    }
    @Transactional
    public StatusDto assignDeliveryPerson(int id) {
        //Find the first Delivery Person who is available
        DeliveryPerson deliverPerson=deliveryPersonService.findAvailableDeliverPerson();
        StatusDto statusDto =new StatusDto();
        //If a delivery person is available assign him to the order
        if(deliverPerson!=null) {
            //Set Delivery Person to not available as he is out on delivery
            deliverPerson.setAvailable(false);
            Delivery delivery = new Delivery();
            delivery.setDeliveryPerson(deliverPerson);
            delivery.setOrderId(id);

            //calculating the approx time when food would be delivered
            int minTimeRequired = 20, maxTimeRequired=60;
            Random randomNum = new Random();
            int addMinuteTime = minTimeRequired + randomNum. nextInt(maxTimeRequired);
            Date targetTime = Calendar.getInstance().getTime();
            targetTime = DateUtils.addMinutes(targetTime, addMinuteTime);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String deliveryDateTime = dateFormat.format(targetTime);
            delivery.setDeliveryDateTime(deliveryDateTime);

            deliveryRepository.save(delivery);
            statusDto.setStatus(true);
            statusDto.setDescription("Delivery person with ID "+deliverPerson.getId()+" assigned to Order ID "+id);
            /*Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY,1);
            return calendar.getTime();*/
        }
        else{
            statusDto.setStatus(false);
            statusDto.setDescription("No delivery person available");
        }
        //RETURN A PROPER MESSAGE
        return statusDto;
    }
}

