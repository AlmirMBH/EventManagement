package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.domain.Period;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
public class PeriodMapper {

    public Period map(PeriodDto dto){

       return Period.builder()
                .startDate(dto.getStart().toLocalDate())
                .endDate(dto.getEnd().toLocalDate())
                .startTime(Time.valueOf(dto.getStart().toLocalTime()))
                .endTime(Time.valueOf(dto.getEnd().toLocalTime()))
                .build();

    }
}
