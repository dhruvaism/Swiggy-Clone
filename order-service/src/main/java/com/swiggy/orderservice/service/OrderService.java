package com.swiggy.orderservice.service;

import com.swiggy.orderservice.dto.*;
import com.swiggy.orderservice.entity.*;
import com.swiggy.orderservice.exception.ApiRequestException;
import com.swiggy.orderservice.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService {

    private OrderItemsRepository orderItemsRepository;
    private OrderRepository orderRepository;
    private PaymentService paymentService;
    private BillRepository billRepository;
    private CustomerRepository customerRepository;
    private CustomerAddressRepository customerAddressRepository;
    private RestaurantRepository restaurantRepository;
    private RestaurantAddressRepository restaurantAddressRepository;

    @Autowired
    public OrderService(OrderItemsRepository orderItemsRepository, OrderRepository orderRepository, PaymentService paymentService, BillRepository billRepository, CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository, RestaurantRepository restaurantRepository, RestaurantAddressRepository restaurantAddressRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.billRepository = billRepository;
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantAddressRepository = restaurantAddressRepository;
    }


   //  3 sql operations, you have done 2 operations, while 3rd opeartion your systems gets failure

    @Transactional
    public Order addOrder(OrderRequestBody orderRequestBody){
        Order order = new Order();

        //validity of payment
        Payment payment = paymentService.getPaymentById(orderRequestBody.getPayment_id());
        if(payment.isHas_order()){
            throw new ApiRequestException("Invalid payment details");
        }

        //validity of order items
        if(orderRequestBody.getOrder_items()==null){
            throw new ApiRequestException("there should be atleast 1 item");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        orderRequestBody.getOrder_items().stream().forEach(orderItem -> {
           //i every order items in request body
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setFood_name(orderItem.getFood_name());

            //TODO -- check existance of food by food_id in restaurant microservice

            orderItem1.setFood_id(orderItem.getFood_id());
            orderItem1.setPrice(orderItem.getPrice());
            orderItem1.setQuantity(orderItem.getQuantity());
            try {
                orderItemsRepository.save(orderItem1); //order items
            }catch (Exception e){
                throw new ApiRequestException(e.getMessage());
            }

            orderItems.add(orderItem1); // order enity has orderitems
        });

        Bill bill = new Bill();
        bill.setDiscount(orderRequestBody.getDiscount());
        bill.setDelivery_charge(orderRequestBody.getDelivery_charge());
        bill.setItem_total(orderRequestBody.getItem_total());
        bill.setOrder_total(orderRequestBody.getOrder_total());

        try {
            bill = billRepository.save(bill);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        order.setBill(bill);
        payment.setHas_order(true);
        order.setPayment(payment);
        order.setOrder_items(orderItems);
        order.setOrder_status(OrderStatus.CONFIRMED);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        order.setOrder_date(strDate);

        //TODO -- validity of customer_id
        // need to call customer microservice


        CustomerAddress customerAddress = new CustomerAddress(123,"55","Taradubi","Hojai","Assam","782429","JUgijan");
        Customer customer = new Customer(45,"Dhrubajit Sarkar", "dhrub@gmail.com","7896914819",customerAddress);

        //save customer

        try {
            customerAddressRepository.save(customerAddress);
            customer = customerRepository.save(customer);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        order.setCustomer(customer);

        //TODO -- validity of restaurant_id
        // need to call Restaurant microservice

        RestaurantAddress restaurantAddress = new RestaurantAddress(15,"Guwahati","Guwahati","Assam","782410","Khanapara");
        Restaurant restaurant = new Restaurant(123,"Once more Hotel",restaurantAddress);

        try{
            restaurantAddressRepository.save(restaurantAddress);
            restaurantRepository.save(restaurant);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        order.setRestaurant(restaurant);



        //order has everything we need
        try {
            order = orderRepository.save(order);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        //TODO -- delete cart by cart _id

       return order;

    }

    @Transactional
    public List<Order> getAllOrders(){ //admin has access
        List<Order> orders;
        try {
            orders = orderRepository.findAll();
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

           return orders;
        }

    @Transactional
    public Order getOrderById(int id){
        Optional<Order> order;
        try {
            order = orderRepository.findById(id);
            if(!order.isPresent()){
                throw new ApiRequestException("No order details found");
            }
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        return order.get();

    }

    @Transactional
    public List<Order> getAllOrdersByCustomerId(final int customer_id){
        List<Order> orders;
        try {
            orders = orderRepository.findAllByCustomer_Id(customer_id);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return orders;
    }

    @Transactional
    public List<Order> getAllOrdersByRestaurant(final int restaurant_id){
        List<Order> orders;
        try {
            orders = orderRepository.findAllByRestaurant_Id(restaurant_id);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return orders;
    }


    @Transactional
    public CheckoutResponse checkout(int customer_id){

        //TODO-- get customer details from Customer microservice


        CheckoutResponse checkoutResponse = new CheckoutResponse();
        ArrayList<CustomerAddress> addresses = new ArrayList<>();
        addresses.add(new CustomerAddress(12,"55","Taradubi","Hojai","Assam","782429","Jugijan"));
        addresses.add(new CustomerAddress(15,"345","Guwahati","Guwahati","Assam","782410","Khanapara"));
        CheckoutCustomer customer = new CheckoutCustomer(45,"Dhrubajit Sarkar", "dhrub@gmail.com","7896914819",addresses);


        //TODO -  get cart detials from cart microservices

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(1,234,"BUtter chiken",160.45,1));
        cartItems.add(new CartItem(2,2244,"naan",160.45,4));
        Cart cart = new Cart(12,234.6,23.5,40,cartItems);

        checkoutResponse.setCart(cart);
        checkoutResponse.setCustomer(customer);

        return checkoutResponse;



    }


}
