package com.swiggy.orderservice.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table (name = "payment")
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transaction_id",unique = true, nullable = false)
    private String transaction_id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String transaction_date;

    @Column(nullable = false)
    private boolean has_order;


    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;






}
