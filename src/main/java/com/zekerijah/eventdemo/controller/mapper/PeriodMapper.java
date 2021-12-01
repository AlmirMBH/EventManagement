package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.domain.Period;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;

@Component
public class PeriodMapper {

    public Period map(PeriodDto dto){

       return Period.builder()
                .startDate(dto.getStart().toLocalDate())
                // getStart(), getEnd() are Lombock generated (see PeriodDto)
                .endDate(dto.getEnd().toLocalDate())
                .startTime(Time.valueOf(dto.getStart().toLocalTime()))
                .endTime(Time.valueOf(dto.getEnd().toLocalTime()))
                .build();
    }

    public PeriodDto map(Period period) {
        return PeriodDto.builder()
                .start(LocalDateTime.of(period.getStartDate(), period.getStartTime().toLocalTime()))
                .end(LocalDateTime.of(period.getEndDate() , period.getEndTime().toLocalTime()))
                .build();
    }

}
