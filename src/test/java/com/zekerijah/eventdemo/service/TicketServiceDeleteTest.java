package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.TicketUtil;
import com.zekerijah.eventdemo.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketServiceDeleteTest extends IntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void whenFoundTicket_thenDelete() {
        //given
        Ticket ticket = ticketService.saveTicket(TicketUtil.generate());
        //when
        ticketService.deleteTicket(ticket.getId());
        //then
        assertThat(ticketRepository.findById(ticket.getId())).isEmpty();
    }

    @Test
    void whenTicketDoesntExists_thenThrowException() {
        //given
        Ticket ticket = TicketUtil.generate();
        //then
        InvalidDataAccessApiUsageException exception = assertThrows(InvalidDataAccessApiUsageException.class, ()->
                ticketService.deleteTicket(ticket.getId()) );
    }
}
