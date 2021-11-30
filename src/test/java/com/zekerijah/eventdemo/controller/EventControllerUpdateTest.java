package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.controller.dto.UpdateEventReq;
import com.zekerijah.eventdemo.controller.mapper.PeriodMapper;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(EventController.class)
public class EventControllerUpdateTest extends WebLayerBase {

    private final String URL = String.format("/events");

    @MockBean private EventService eventService;
    PeriodMapper periodMapper = new PeriodMapper();

    @Test
    void whenValidRequest_thenUpdateEvent(){
        //given
        PeriodDto periodDto = PeriodDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .build();


        final UpdateEventReq req = new UpdateEventReq().builder()
                .id(1L)
                .title("Dummy Title")
                .description("Dummy Description")
                .period(periodDto)
                .build();

        //when
        when(eventService.updateEvent(any(Event.class)))
                .thenReturn(Event.builder()
                        .id(req.getId())
                        .title(req.getTitle())
                        .description(req.getDescription())
                        .period(periodMapper.map(req.getPeriod()))
                        .build());

        final MockHttpServletResponse response = doPut(URL, req);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(eventService).updateEvent(any(Event.class));


    }
}
