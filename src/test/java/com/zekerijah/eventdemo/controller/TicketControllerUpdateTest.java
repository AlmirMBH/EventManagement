package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.controller.dto.UpdateTicketReq;
import com.zekerijah.eventdemo.controller.mapper.PeriodMapper;
import com.zekerijah.eventdemo.domain.Ticket;
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
import static org.mockito.Mockito.when;

@WebMvcTest(TicketController.class)
public class TicketControllerUpdateTest extends WebLayerBase {

    private final String URL = String.format("/tickets");

    @MockBean
    private TicketService ticketService;
    PeriodMapper periodMapper = new PeriodMapper();

    @Test
    void whenValidRequest_thenUpdate() {
        PeriodDto periodDto = PeriodDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .build();

        UpdateTicketReq req = UpdateTicketReq.builder()
                .id(1L)
                .name("Dummy Name")
                .price(20.00)
                .quantityAvailable(100)
                .period(periodDto)
                .build();

        when(ticketService.updateTicket(any(Ticket.class)))
                .thenReturn(Ticket.builder()
                        .id(req.getId())
                        .name(req.getName())
                        .price(req.getPrice())
                        .quantityAvailable(req.getQuantityAvailable())
                        .period(periodMapper.map(req.getPeriod()))
                        .build());

        final MockHttpServletResponse response = doPut(URL, req);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(ticketService).updateTicket(any(Ticket.class));
    }
}
