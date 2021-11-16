package com.zekerijah.eventdemo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Period {
    private LocalDate startDate;

    private LocalDate endDate;

    private Time startTime;

    private Time endTime;

    @PrePersist
    private void prePersist() {
        validate();
    }

    @PreUpdate
    private void preUpdate() {
        validate();
    }

    private void validate() {
        if (startDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Start date is before start now");
        } else if (endDate.isBefore(startDate)) {
            throw new RuntimeException("End date is before start date");
        } else if (endTime.toLocalTime().isBefore(LocalTime.now())) {
            throw new RuntimeException("Start time is before start now");
        } else if (endTime.toLocalTime().isBefore(startTime.toLocalTime())) {
            throw new RuntimeException("End time is before start time");
        }
    }
}
