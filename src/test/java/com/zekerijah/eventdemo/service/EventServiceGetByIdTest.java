package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.controller.mapper.PeriodMapper;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.domain.Period;

import static com.zekerijah.eventdemo.controller.handler.ErrorCode.EVENT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// @AllArgsConstructor
public class EventServiceGetByIdTest extends IntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    void whenEventExist_thenReturn() {

        Period period = PeriodUtil.generate();

        //given
        final Event event = Event.builder()
                .title("Dummy Title")
                .description("Dummy Description")
                .period(period)
                .build();

        eventService.saveEvent(event);

        //when
        final Event createdEvent = eventRepository.getById(event.getId());

        //then
        assertThat(createdEvent).isNotNull();
        assertThat(createdEvent.getId()).isEqualTo(event.getId());
    }

    @Test
    void whenEventDoesNotExists_thenThrowException() {
        //when
        final EventDemoException exception = assertThrows(EventDemoException.class, ()-> eventService.findEvent(300L));
        //then
        assertThat(exception.getMessage()).isEqualTo("Event not found");
    }



}
