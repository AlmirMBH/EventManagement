package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.CreateEventReq;
import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@WebMvcTest(EventController.class)
public class EventControllerSaveTest extends WebLayerBase {

    private final String URL = String.format("/events"); // method location

    @MockBean private EventService eventService; // saves data in Spring memory

    @Test
    void whenValidRequest_thenSaveProduct(){
        PeriodDto periodDto = PeriodDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .build();

        final CreateEventReq req = CreateEventReq.builder()
                .title("Dummy Title")
                .description("Dummy Description")
                .period(periodDto)
                .build();

        final MockHttpServletResponse response = doPost(URL, req);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        verify(eventService).saveEvent(any());
    }
}
