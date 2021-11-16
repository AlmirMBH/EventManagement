package com.zekerijah.eventdemo;

import com.zekerijah.eventdemo.domain.Period;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PeriodUtil {

    public static Period generate() {
        return Period.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .startTime(Time.valueOf(LocalTime.now()))
                .endTime(Time.valueOf(LocalTime.now().plusHours(1)))
                .build();
    }

    public static Period generatePeriodWithStartDateBeforeNow() {
        return Period.builder()
                .startDate(LocalDate.now().plusDays(2))
                .endDate(LocalDate.now().plusDays(1))
                .startTime(Time.valueOf(LocalTime.now()))
                .endTime(Time.valueOf(LocalTime.now().plusHours(1)))
                .build();
    }

    public static Period generatePeriodWithStartTimeBeforeNow() {
        return Period.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .startTime(Time.valueOf(LocalTime.now().plusHours(2)))
                .endTime(Time.valueOf(LocalTime.now().plusHours(1)))
                .build();
    }
}
