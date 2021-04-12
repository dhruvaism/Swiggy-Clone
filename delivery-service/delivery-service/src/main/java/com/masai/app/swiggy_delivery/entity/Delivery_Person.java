package com.masai.app.swiggy_delivery.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Delivery_Person {
    @Id
    private String id;
    private String name;
    private long phone_no;
    private boolean isAvailable;
    private int rating_count;
    @Column(precision=2, scale=1)
    private double rating;

}
