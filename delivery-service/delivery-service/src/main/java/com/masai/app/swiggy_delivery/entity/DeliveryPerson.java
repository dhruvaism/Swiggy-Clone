package com.masai.app.swiggy_delivery.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long phone_no;
    private boolean isAvailable;
    private int rating_count;

    @Column(precision=2, scale=1)
    private double rating;

    @JsonManagedReference
    @OneToMany(mappedBy="deliveryPerson") // One Delivery Person can do multiple Deliveries
    private List<Delivery> deliveries=new ArrayList<>();

}
