package com.masai.app.swiggy_delivery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int orderId;
    private String deliveryDateTime;
    @JsonBackReference
    @ManyToOne //One Delivery is done by a single Delivery Person
    private DeliveryPerson deliveryPerson;

}
