package com.masai.app.swiggy_delivery.dto;

import com.masai.app.swiggy_delivery.entity.DeliveryPerson;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryDto {
        private int id;
        private DeliveryPerson deliveryPerson;
        private int orderId;
        private String deliveryDateTime;
}
