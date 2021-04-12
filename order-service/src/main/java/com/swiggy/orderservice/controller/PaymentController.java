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

    @PostMapping("/api/v1/payment-method") //Access = (ADMIN)
    public ResponseEntity<Response> addPaymentMethod( @RequestBody final PaymentMethod paymentMethod){
         PaymentMethod paymentMethod1 = paymentService.addPaymentMethod(paymentMethod);
         return new ResponseEntity<>(new Response(paymentMethod1,HttpStatus.OK), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/payment-method") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> getAllPaymentMethod(){
        List<PaymentMethod> paymentMethods = paymentService.getAllPaymentMethod();
        return new ResponseEntity<>(new Response(paymentMethods,HttpStatus.OK), HttpStatus.FOUND);
    }

    @GetMapping("/api/v1/payment-method/{payment_method_id}") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> getPaymentMethodById(@PathVariable final int payment_method_id){
        PaymentMethod paymentMethod = paymentService.getPaymentMethod(payment_method_id);
        return new ResponseEntity<>(new Response(paymentMethod,HttpStatus.OK), HttpStatus.FOUND);
    }

    @PostMapping("/api/v1/payment") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> addPayment(@RequestBody final PaymentRequestBody paymentRequestBody){
        Payment payment1 = paymentService.addPayment(paymentRequestBody);
        return new ResponseEntity<>(new Response(payment1,HttpStatus.OK),HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/payment") //Access = (ADMIN)
    public ResponseEntity<Response> getAllPayments(){
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(new Response(payments,HttpStatus.OK),HttpStatus.FOUND);
    }

    @GetMapping("/api/v1/payment/{payment_id}") //Access = (ADMIN,CUSTOMER)
    public ResponseEntity<Response> getPaymentById(@PathVariable final int payment_id){
        Payment payment1 = paymentService.getPaymentById(payment_id);
        return new ResponseEntity<>(new Response(payment1,HttpStatus.OK),HttpStatus.FOUND);
    }

    @DeleteMapping("/api/v1/payment-method")
    public  ResponseEntity<Response> deletePaymentMethodById(@RequestParam("id") int id){
        String res = paymentService.deletePaymentMethodById(id);
        return new ResponseEntity<>(new Response(res,HttpStatus.OK),HttpStatus.OK);
    }


    /**
     *
     * @param payment_method_id: id of that payment method to be updated
     * @param paymentMethod: information to be updated
     * @return: updated payment method
     */

    @PutMapping("/api/v1/payment-method/{payment_method_id}/update")
    public  ResponseEntity<Response> updatePaymentMethodById(@PathVariable final int payment_method_id, @RequestBody final PaymentMethod paymentMethod){
        PaymentMethod paymentMethod1 = paymentService.updatePaymentMethodById(payment_method_id,paymentMethod);
        return new ResponseEntity<>(new Response(paymentMethod1,HttpStatus.OK),HttpStatus.OK);
    }





}
