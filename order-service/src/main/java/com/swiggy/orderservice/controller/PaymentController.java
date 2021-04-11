package com.swiggy.orderservice.controller;


import com.swiggy.orderservice.dto.PaymentRequestBody;
import com.swiggy.orderservice.entity.Payment;
import com.swiggy.orderservice.entity.PaymentMethod;
import com.swiggy.orderservice.entity.Response;
import com.swiggy.orderservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment-method") //Access = (ADMIN)
    public ResponseEntity<Response> addPaymentMethod( @RequestBody final PaymentMethod paymentMethod){
         if(paymentMethod == null) throw new RuntimeException("missing requestbody");
         PaymentMethod paymentMethod1 = paymentService.addPaymentMethod(paymentMethod);
         return new ResponseEntity<>(new Response(paymentMethod1,HttpStatus.OK), HttpStatus.CREATED);
    }

    @GetMapping("/payment-method") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> getAllPaymentMethod(){
        List<PaymentMethod> paymentMethods = paymentService.getAllPaymentMethod();
        return new ResponseEntity<>(new Response(paymentMethods,HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/payment-method/{payment_method_id}") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> getPaymentMethodById(@PathVariable final int payment_method_id){
        PaymentMethod paymentMethod = paymentService.getPaymentMethod(payment_method_id);
        return new ResponseEntity<>(new Response(paymentMethod,HttpStatus.OK), HttpStatus.FOUND);
    }

    @PostMapping("/payment") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> addPayment(@RequestBody final PaymentRequestBody paymentRequestBody){
        Payment payment1 = paymentService.addPayment(paymentRequestBody);
        return new ResponseEntity<>(new Response(payment1,HttpStatus.OK),HttpStatus.CREATED);
    }

    @GetMapping("/payment") //Access = (ADMIN)
    public ResponseEntity<Response> getAllPayments(){
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(new Response(payments,HttpStatus.OK),HttpStatus.FOUND);
    }

    @GetMapping("/payment/{payment_id}") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> getPaymentById(@PathVariable final int payment_id){
        Payment payment1 = paymentService.getPaymentById(payment_id);
        return new ResponseEntity<>(new Response(payment1,HttpStatus.OK),HttpStatus.CREATED);
    }


}
