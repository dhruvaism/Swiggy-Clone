package com.swiggy.orderservice.service;

import com.swiggy.orderservice.dto.PaymentRequestBody;
import com.swiggy.orderservice.entity.Payment;
import com.swiggy.orderservice.entity.PaymentMethod;
import com.swiggy.orderservice.exception.ApiRequestException;
import com.swiggy.orderservice.repository.PaymentMethodRepository;
import com.swiggy.orderservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMethodRepository paymentMethodRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    //add payment method
    public PaymentMethod addPaymentMethod(PaymentMethod paymentMethod){

        PaymentMethod paymentMethod1 = paymentMethodRepository.save(paymentMethod);
        return paymentMethod1;

    }

    //get all payment methods
    public List<PaymentMethod> getAllPaymentMethod(){
        return paymentMethodRepository.findAll();
    }

    //get payment method by Id
    public PaymentMethod getPaymentMethod(int id){
        return paymentMethodRepository.findById(id).get();
    }

    //add Payment
    public Payment addPayment(PaymentRequestBody paymentRequestBody){
        Payment payment = new Payment();
        payment.setAmount(paymentRequestBody.getAmount());
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        payment.setTransaction_date(strDate);
        payment.setTransaction_id(paymentRequestBody.getTransaction_id());

        Optional<PaymentMethod> optional = paymentMethodRepository.findById(paymentRequestBody.getPayment_method_id());
        if(optional.isPresent()){
            payment.setPaymentMethod(optional.get());
            return paymentRepository.save(payment);
        }else{
            throw new ApiRequestException("Invalid Payment method");
        }

    }

    //get all payments
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    //get Payment by id
    public Payment getPaymentById(int id){
        Optional<Payment> optional = paymentRepository.findById(id);
        if(optional.isEmpty()){
            throw new ApiRequestException("There isn't exist any payment details");
        }else{
            return optional.get();
        }
    }


}
