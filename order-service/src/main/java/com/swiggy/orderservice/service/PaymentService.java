package com.swiggy.orderservice.service;

import com.swiggy.orderservice.dto.PaymentRequestBody;
import com.swiggy.orderservice.entity.Payment;
import com.swiggy.orderservice.entity.PaymentMethod;
import com.swiggy.orderservice.exception.ApiRequestException;
import com.swiggy.orderservice.repository.PaymentMethodRepository;
import com.swiggy.orderservice.repository.PaymentRepository;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

        if(paymentMethod.getName()==null){
            throw new ApiRequestException("Incomplete request body!");
        }

        PaymentMethod paymentMethod1;
        try {
            paymentMethod1 = paymentMethodRepository.save(paymentMethod);
        }catch (DataIntegrityViolationException e){
            throw new ApiRequestException("Payment method already exists!");
        }catch (Exception e){
            throw  new ApiRequestException("Some error occurred!");
        }
        return paymentMethod1;

    }

    //get all payment methods
    public List<PaymentMethod> getAllPaymentMethod(){
        try {
            return paymentMethodRepository.findAll();
        }catch (Exception e){
            throw new ApiRequestException("Some error occurred!");
        }

    }

    //get payment method by Id
    public PaymentMethod getPaymentMethod(int id){
        Optional<PaymentMethod> paymentMethod;
        try {
            paymentMethod = paymentMethodRepository.findById(id);
        }catch (Exception e){
            throw new ApiRequestException("Some error occurred!");
        }
        if(paymentMethod.isEmpty()){
            throw new ApiRequestException("Payment method doesn't exist!");
        }
        return paymentMethod.get();
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
        try{
            Optional<PaymentMethod> optional = paymentMethodRepository.findById(paymentRequestBody.getPayment_method_id());
            if(optional.isPresent()){
                payment.setPaymentMethod(optional.get());
                payment = paymentRepository.save(payment);
                return payment;
            }else{
                throw new ApiRequestException("Invalid Payment method!");
            }
        }catch (Exception e){
            throw new ApiRequestException("Invalid request body!");
        }

    }

    //get all payments
    public List<Payment> getAllPayments(){
        try {
            return paymentRepository.findAll();
        }catch (Exception e){
            throw new ApiRequestException("Some error occurred!");
        }
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

    //delete payment-method by id
    public String deletePaymentMethodById(int id){

        Optional<PaymentMethod> pm;
        try {
            pm = paymentMethodRepository.findById(id);
            if(pm.isEmpty()){
                throw new ApiRequestException("There isn't exist any payment method details");
            }else{
                paymentMethodRepository.delete(pm.get());
            }
        }catch (Exception e){
            throw new ApiRequestException("Some error occurred!");
        }

        return "Deleted successfully";
    }

    //update payment-method by id

    public PaymentMethod updatePaymentMethodById(int paymentMethodId, PaymentMethod paymentMethod){

        try{
            Optional<PaymentMethod> paymentMethod1 = paymentMethodRepository.findById(paymentMethodId);
            if(paymentMethod1.isPresent()){
                PaymentMethod paymentMethod2 = paymentMethod1.get();
                if(paymentMethod.getName()!=null){
                    paymentMethod2.setName(paymentMethod.getName());
                }

                if(paymentMethod.getDiscount_available()>0.0){
                    paymentMethod2.setDiscount_available(paymentMethod.getDiscount_available());
                }
                paymentMethodRepository.save(paymentMethod2);
                return paymentMethod2;

            }else{
                throw new ApiRequestException("There isn't exist any payment method!");
            }
        }catch (Exception e){
            throw new ApiRequestException("Some error occurred!");
        }

    }






}
