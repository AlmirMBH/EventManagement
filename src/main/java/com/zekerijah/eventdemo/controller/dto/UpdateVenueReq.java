package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

// This class defines the form of request to the Venue class
public class UpdateVenueReq {
    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String venueName;
    @NotBlank // The @NotBlank annotation uses the NotBlankValidator class, which checks that a character sequence's trimmed length is not empty
    private String addressLine1;
    @NotBlank
    private String addressLine2;
    @NotBlank
    private String city;
    @NotBlank
    private String postCode;

    @Override
    public String toString() {
        return "UpdateVenueReq{" +
                "id=" + id +
                ", venue name ='" + venueName + '\'' +
                ", address line 1 ='" + addressLine1 + '\'' +
                ", address line 2 ='" + addressLine2 + '\'' +
                ", city =" + city + '\'' +
                ", post code =" + postCode +
                '}';
    }
}
