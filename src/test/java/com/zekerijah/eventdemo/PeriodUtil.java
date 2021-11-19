package com.zekerijah.eventdemo;

import com.zekerijah.eventdemo.domain.Period;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class PeriodUtil {

    public static Period generate() {
        return Period.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .startTime(Time.valueOf(LocalTime.now().plusHours(1)))
                .endTime(Time.valueOf(LocalTime.now().plusHours(2)))
                .build();
    }

    public static Period generatePeriodWithEndDateBeforeStartDate() {
        return Period.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().minusDays(1))
                .startTime(Time.valueOf(LocalTime.now()))
                .endTime(Time.valueOf(LocalTime.now().plusHours(1)))
                .build();
    }

    public static Period generatePeriodPresentDateEndTimeBeforeStartTime() {
        return Period.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .startTime(Time.valueOf(LocalTime.now().plusHours(2)))
                .endTime(Time.valueOf(LocalTime.now().plusHours(1)))
                .build();
    }
}
