package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.TicketController;
import com.zekerijah.eventdemo.controller.WebLayerBase;
import com.zekerijah.eventdemo.controller.dto.CreateTicketReq;
import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@WebMvcTest(TicketController.class)
public class TicketControllerSaveTest extends WebLayerBase {

    @MockBean
    private TicketService ticketService;

    private final String URL = String.format("/tickets");

    @Test
    void whenValidRequest_thenSave(){

        PeriodDto periodDto = PeriodDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .build();

        CreateTicketReq req = CreateTicketReq.builder()
                .name("Dummy Name")
                .price(20.00)
                .quantityAvailable(100)
                .period(periodDto)
                .build();

        final MockHttpServletResponse response = doPost(URL, req);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        verify(ticketService).saveTicket(any());


    }
}
