package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateTicketRes {
    private Long id;
    private String name;
    private Double price;
    private Integer quantityAvailable;
    private PeriodDto period;
}
