package com.swiggy.orderservice.dto;

import lombok.Getter;

@Getter
public class PaymentRequestBody {

    private String transaction_id;
    private int amount;
    private int payment_method_id;


}
