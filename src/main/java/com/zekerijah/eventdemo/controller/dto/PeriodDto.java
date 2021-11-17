package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeriodDto {
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;

    @Override
    public String toString() {
        return "PeriodDto{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
