package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.domain.Ticket;
import com.zekerijah.eventdemo.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@WebMvcTest(TicketController.class)
public class TicketControllerDeleteTest extends WebLayerBase{

    @MockBean
    private TicketService ticketService;

    private final Long ID =1L;
    private final String URL = String.format("/tickets/%d", ID);

    @Test
    void whenValidRequest_thenDelete(){

        Ticket ticket = Ticket.builder()
                .id(1L)
                .name("Dummy Name")
                .price(20.00)
                .quantityAvailable(300)
                .period(PeriodUtil.generate())
                .build();

        final MockHttpServletResponse response = doDelete(URL);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(ticketService).deleteTicket(ticket.getId());
    }

}
