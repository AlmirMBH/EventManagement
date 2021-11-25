package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.EventUtil;
import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.domain.Period;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventServiceUpdateTest extends IntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    void whenEventExists_thenUpdate() {
        // given
        Period period =  PeriodUtil.generate();

        Event existing = eventRepository.save(EventUtil.generate());

        Event updateExisting = Event.builder()
                .id(existing.getId())
                .title("Updated")
                .description(existing.getDescription())
                .period(period)
                .build();

        //when
        Event result = eventService.updateEvent(updateExisting);

        //then
        assertThat(updateExisting).isEqualTo(result);
        assertThat(updateExisting.getTitle()).isEqualTo(result.getTitle());
    }

    @Test
    void whenEventDoesNotExists_thenThrowException(){
        //given
        Event event = Event.builder()
                .id(999999L)
                .period(PeriodUtil.generate())
                .title("Dummy")
                .description("Dummy")
                .build();

        //then
        EventDemoException exception = assertThrows(EventDemoException.class, ()-> eventService.updateEvent(event));
        assertThat(exception.getMessage()).isEqualTo("Event not found");

    }
}
