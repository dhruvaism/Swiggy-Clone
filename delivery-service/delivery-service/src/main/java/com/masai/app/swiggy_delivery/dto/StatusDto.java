package com.masai.app.swiggy_delivery.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusDto {
    private boolean status;
    private String description;
}
