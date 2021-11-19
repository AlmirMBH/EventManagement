package com.zekerijah.eventdemo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.zekerijah.eventdemo.controller.handler.ErrorCode.*;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Period {
    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Time startTime;

    @Column(nullable = false)
    private Time endTime;

    @PrePersist
    private void prePersist() {
        validate();
    }

    @PreUpdate
    private void preUpdate() {
        validate();
    }

    public void validate() {
        // TODO
        // Create test for this method

        // START DATE 2021-11-17
        // END DATE 2021-11-18
        // START TIME 22:52:49
        // END TIME 00:52:49
        if (endDate.isBefore(startDate)) {
            throw new EventDemoException(END_DATE_IS_BEFORE_START_DATE);
        } else if (endDate.isEqual(startDate)) {
            if ((endTime.toLocalTime().isBefore(startTime.toLocalTime()))) {
                throw  new EventDemoException(PRESENT_DATE_END_TIME_IS_BEFORE_START_TIME);
            }
        }
    }
}
