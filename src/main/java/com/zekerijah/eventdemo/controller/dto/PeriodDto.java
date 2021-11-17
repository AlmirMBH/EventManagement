package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeriodDto {
    private LocalDateTime start;
    private LocalDateTime end;

}
