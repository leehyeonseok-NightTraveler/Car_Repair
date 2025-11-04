package com.boot.dto;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class RecommendDTO {
	
	private String storeId;       // store_id
    private String password;      // password
    private String email;         // email
    private String phoneNumber;   // phone_number
    private Date regDate;         // reg_date
    private String address;       // address
    private double latitude;      // latitude
    private double longitude;     // longitude
    private String description;   // description
    private String openingHours;  // opening_hours
}
