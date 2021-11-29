package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UpdateTicketRes {
    private Long id;
    private String name;
    private Double price;
    private Integer quantityAvailable;
    private PeriodDto periodDto;
}
