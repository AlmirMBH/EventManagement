package com.zekerijah.eventdemo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import static com.zekerijah.eventdemo.controller.handler.ErrorCode.*;

@Getter
@Setter
@Embeddable
// allows the class to be included into another entity without creating a separate table
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

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period)) return false;
        Period period = (Period) o;
        return Objects.equals(getStartDate(), period.getStartDate()) && Objects.equals(getEndDate(), period.getEndDate()) && Objects.equals(getStartTime(), period.getStartTime()) && Objects.equals(getEndTime(), period.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate(), getStartTime(), getEndTime());
    }
}
