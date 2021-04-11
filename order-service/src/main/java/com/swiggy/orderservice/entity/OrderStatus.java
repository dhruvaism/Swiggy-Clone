package com.swiggy.orderservice.entity;

public enum OrderStatus {

    CONFIRMED,COOKING,ARRIVING,DELIVERED;

    //CONFIRMED -> customer made an order (done by customer)

    //COOKING ->  admin assign some delivery person (admin)
    //ARRIVING -> deliver person picked up the order from restaurant (admin)
    //DELIVERY -> order got delivered (admin)

    //implemented in delivery microservice


    //manually do this
    //Push notification (Kafka)
}
