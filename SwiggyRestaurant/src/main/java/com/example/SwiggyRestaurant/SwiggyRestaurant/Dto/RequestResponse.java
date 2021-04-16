package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponse {
	
	Object Data;
	StatusDto updateStatus;
	
}
