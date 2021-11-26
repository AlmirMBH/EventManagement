package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.TicketUtil;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketServiceUpdateTest extends IntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void whenTicketExists_thenUpdate(){
        //given
        Ticket existing = ticketRepository.save(TicketUtil.generate());

        Ticket updateExisting = Ticket.builder()
                .id(existing.getId())
                .name("Update name")
                .quantityAvailable(existing.getQuantityAvailable())
                .period(existing.getPeriod())
                .price(existing.getPrice())
                .build();
        //when
        Ticket result = ticketService.updateTicket(updateExisting);
        //then
        assertThat(updateExisting).isEqualTo(result);
        assertThat(updateExisting.getName()).isEqualTo(result.getName());
    }

    @Test
    void whenTicketDoesntExists_thenThrowException() {
        //when
        Ticket ticket = Ticket.builder()
                .id(1L)
                .name("Dummy Name")
                .price(20.00)
                .quantityAvailable(400)
                .build();
        //then
        EventDemoException exception = assertThrows(EventDemoException.class, ()-> ticketService.updateTicket(ticket));
        assertThat(exception.getMessage()).isEqualTo("Ticket not found");



    }
}
