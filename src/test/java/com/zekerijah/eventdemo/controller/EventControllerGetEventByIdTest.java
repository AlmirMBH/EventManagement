package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.handler.ErrorCode;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(EventController.class)
class EventControllerGetEventByIdTest extends WebLayerBase {

    @MockBean private EventService eventService;

    private final Long EVENT_ID = 1L;
    private final String URL = String.format("/events/%d", EVENT_ID);

    @Test
    void whenValidRequest_thenReturnStatusOk() {
        // when
        MockHttpServletResponse response = doGet(URL);

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(eventService).findEvent(EVENT_ID);
    }

    @Test
    void whenInvalidRequest_thenReturnStatusBadRequest() {
        // given
        doThrow(new EventDemoException(ErrorCode.BAD_REQUEST_CODE))
                .when(eventService).findEvent(EVENT_ID);

        // when
        MockHttpServletResponse response = doGet(URL);

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        verify(eventService).findEvent(EVENT_ID);
    }



}
