package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketServiceSaveTest extends IntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void whenValidTicket_thenSave(){

        Period period = PeriodUtil.generate();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(20.00)
                .quantityAvailabel(300)
                .build();

        Ticket result = ticketService.saveTicket(ticket);

        Ticket persisted = ticketRepository.getById(result.getId());

        assertThat(result).isEqualTo(persisted);
        assertThat(result.getEvent()).isNull();
    }
}
