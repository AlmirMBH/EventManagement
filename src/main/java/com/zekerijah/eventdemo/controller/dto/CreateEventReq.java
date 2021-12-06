package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventReq {
    @NotBlank // mandatory field cannot be empty string
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private PeriodDto period;
    // PeriodDto is specified because of tests, so that the user cannot influence the way the data are inserted into the DB
    // Unlike PeriodDto (only start and end), class Periods contains start date and time and end date and time

    @Override // from Object class
    public String toString() {
        return "CreateEventReq{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", period=" + period +
                '}';
    }
}
