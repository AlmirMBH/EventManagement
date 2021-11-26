package com.zekerijah.eventdemo.controller.dto;

import com.zekerijah.eventdemo.domain.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EventCreatedResDto {
    private Long id;
    private String title;
    private String description;
    private PeriodDto period;
}
