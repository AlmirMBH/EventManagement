package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@WebMvcTest(EventController.class)
public class EventControllerDeleteTest extends WebLayerBase {

    @MockBean
    private EventService eventService;

    private final Long TICKET_ID = 1L;
    private final String URL = String.format("/events/%d", TICKET_ID);

    @Test
    void whenValidRequest_thenDelete(){

        Event event = Event.builder()
                .title("Dummy Title")
                .description("Dummy Description")
                .id(1L)
                .period(PeriodUtil.generate())
                .build();


        final MockHttpServletResponse response = doDelete(URL);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(eventService).deleteEvent(TICKET_ID);
    }


}
