package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor

public class CreateVenueRes {
    private Long id;
    private String venueName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;
}
