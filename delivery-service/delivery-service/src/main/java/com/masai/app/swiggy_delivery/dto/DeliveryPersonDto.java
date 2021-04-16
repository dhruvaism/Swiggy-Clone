package com.masai.app.swiggy_delivery.dto;

import com.masai.app.swiggy_delivery.entity.Delivery;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryPersonDto {
    private int id;
    private String name;
    private long phoneNo;
    private String vehicleType;
    private String vehicleNo;
    private boolean isAvailable;
    private int ratingCount;
    private double rating;
    private List<Delivery> deliveries=new ArrayList<>();
}
