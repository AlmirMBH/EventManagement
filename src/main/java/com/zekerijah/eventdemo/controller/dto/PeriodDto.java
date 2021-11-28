package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodDto)) return false;
        PeriodDto periodDto = (PeriodDto) o;
        return Objects.equals(getStart(), periodDto.getStart()) && Objects.equals(getEnd(), periodDto.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }
}
