package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.TicketUtil;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketServiceGetByIdTest extends IntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void whenTicketExists_thenReturn(){
        //when
        Ticket exist = ticketService.saveTicket(TicketUtil.generate());
        //given
        Ticket createdTicket = ticketRepository.getById(exist.getId());
        //then
        assertThat(createdTicket).isNotNull();
        assertThat(createdTicket.getId()).isEqualTo(exist.getId());

    }

    @Test
    void whenTicketDoesntExists_thenReturnException(){
        //when
        EventDemoException exception = assertThrows(EventDemoException.class, () -> ticketService.findTicket(1L));
        //then
        assertThat(exception.getMessage()).isEqualTo("Ticket not found");
    }
}
