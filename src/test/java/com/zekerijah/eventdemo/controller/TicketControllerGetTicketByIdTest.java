package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.handler.ErrorCode;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@WebMvcTest(TicketController.class)
public class TicketControllerGetTicketByIdTest extends WebLayerBase {

    @MockBean
    private TicketService ticketService;

    private final Long TICKET_ID = 1L;
    private final String URL = String.format("/tickets/%d", TICKET_ID);

    @Test
    void whenValidRequest_thenReturnStatusOk() {
        //given
        MockHttpServletResponse response = doGet(URL);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(ticketService).findTicket(TICKET_ID);
    }

    @Test
    void whenInvalidRequest_thenReturnStatusBedRequest(){
        //given
        doThrow(new EventDemoException(ErrorCode.BAD_REQUEST_CODE))
                .when(ticketService).findTicket(TICKET_ID);

        //when
        MockHttpServletResponse response = doGet(URL);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        verify(ticketService).findTicket(TICKET_ID);
    }
}
