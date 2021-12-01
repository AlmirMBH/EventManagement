package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter // Lombock generated getters
@Builder // Lombock generated builder pattern
@AllArgsConstructor // Lombock generated constructor with all class fields
@NoArgsConstructor // Lombock generated empty constructor

public class CreateVenueReq {

    @NotBlank // mandatory field cannot be empty string
    private String venueName;
    @NotBlank
    private String addressLine1;
    @NotBlank
    private String addressLine2;
    @NotBlank
    private String city;
    @NotBlank
    private String postCode;

    @Override // from Object class
    public String toString() {
        return "CreateVenueReq{" +
                ", venue name ='" + venueName + '\'' +
                ", address line 1 ='" + addressLine1 + '\'' +
                ", address line 2 ='" + addressLine2 + '\'' +
                ", city =" + city + '\'' +
                ", post code =" + postCode +
                '}';
    }

}
