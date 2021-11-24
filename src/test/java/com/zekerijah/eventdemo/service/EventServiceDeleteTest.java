package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.EventUtil;
import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.domain.Event;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventServiceDeleteTest extends IntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    void whenFoundEvent_thenDelete() {
        //given
        Event event = eventRepository.save(EventUtil.generate());

        // when
        eventService.deleteEvent(event.getId());

        // then
        assertThat(eventRepository.findById(event.getId())).isEmpty();

    }

}
