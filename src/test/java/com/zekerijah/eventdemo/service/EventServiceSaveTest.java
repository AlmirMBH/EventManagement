package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.Period;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
class EventServiceSaveTest extends IntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    void whenValidEvent_thenSave() {
        // given
        Period period = PeriodUtil.generate();

        Event event = Event.builder()
                .title("Dummy event")
                .description("Dummy description")
                .period(period)
                .build();
        // when
        Event result = eventService.saveEvent(event);

        // then
        Event persisted = eventRepository.getById(result.getId());

        assertThat(result).isEqualTo(persisted);
        assertThat(result.getTickets()).isNull();
    }

    @Test
    void whenEventEndDateBeforeStartDate_thenThrowException() {
        // given
        Period period = PeriodUtil.generate();

        Event event = Event.builder()
                .title("Dummy event")
                .description("Dummy description")
                .period(period)
                .build();

        // when && then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> eventService.saveEvent(event));

        assertThat(exception.getMessage()).isEqualTo("End date is before start date");
    }

    @Test
    void whenEventEndTimeBeforeStartTime_thenThrowException() {
        // given
        Period period = PeriodUtil.generate();

        Event event = Event.builder()
                .title("Dummy event")
                .description("Dummy description")
                .period(period)
                .build();

        // when && then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> eventService.saveEvent(event));

        assertThat(exception.getMessage()).isEqualTo("End time is before start time");
    }

    @Test
    void whenStartDateBeforeNow_thenThrowException() {
        // given
        Period period = PeriodUtil.generatePeriodWithStartDateBeforeNow();

        Event event = Event.builder()
                .title("Dummy event")
                .description("Dummy description")
                .period(period)
                .build();

        // when && then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> eventService.saveEvent(event));

        assertThat(exception.getMessage()).isEqualTo("End time is before start time");
    }

    @Test
    void whenStartTimeBeforeNow_thenThrowException() {
        // given
        Period period = PeriodUtil.generatePeriodWithStartTimeBeforeNow();

        Event event = Event.builder()
                .title("Dummy event")
                .description("Dummy description")
                .period(period)
                .build();

        // when && then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> eventService.saveEvent(event));

        assertThat(exception.getMessage()).isEqualTo("End time is before start time");
    }
}
