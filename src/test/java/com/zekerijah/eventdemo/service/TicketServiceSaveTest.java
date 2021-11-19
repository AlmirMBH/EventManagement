package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketServiceSaveTest extends IntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void whenValidTicket_thenSave(){
        //given
        Period period = PeriodUtil.generate();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(20.00)
                .quantityAvailable(300)
                .period(period)
                .build();

        //when
        Ticket result = ticketService.saveTicket(ticket);

        //then
        Ticket persisted = ticketRepository.getById(result.getId());

        assertThat(result).isEqualTo(persisted);
        assertThat(result.getEvent()).isNull();
    }

    @Test
    void whenTicketEndSaleDateBeforeStartSaleDate_thenThrowException(){
        //given
        Period period = PeriodUtil.generatePeriodWithEndDateBeforeStartDate();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(20.00)
                .quantityAvailable(400)
                .period(period)
                .build();

        //when && then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.saveTicket(ticket) );

        assertThat(exception.getMessage()).isEqualTo("End date is before start date");
    }

    @Test
    void whenPresentTicketEndTimeIsBeforeStartTime_thenThrowException(){
        //given
        Period period = PeriodUtil.generatePeriodPresentDateEndTimeBeforeStartTime();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(20.00)
                .quantityAvailable(400)
                .period(period)
                .build();

        EventDemoException exception = assertThrows(EventDemoException.class, ()-> ticketService.saveTicket(ticket));
        assertThat(exception.getMessage()).isEqualTo("In present date end time is before start time");
    }

    @Test
    void whenTicketNameIsNull_thenThrowException(){
        //given
        Period period = PeriodUtil.generate();

        Ticket ticket = Ticket.builder()
                .name(null)
                .period(period)
                .price(20.00)
                .quantityAvailable(333)
                .build();

        // when && then
        assertThrows(DataIntegrityViolationException.class, ()-> ticketService.saveTicket(ticket));
    }

    @Test
    void whenTicketPriceIsNull_thenThrowException(){
        //given
        Period period = PeriodUtil.generate();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(null)
                .period(period)
                .quantityAvailable(1)
                .build();

        // when && then
        assertThrows(DataIntegrityViolationException.class, ()-> ticketService.saveTicket(ticket));

    }

    @Test
    void whenQuantityAvailableIsNull_thenThrowException(){
        //given
        Period period = PeriodUtil.generate();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .period(period)
                .quantityAvailable(null)
                .price(2.3)
                .build();

        // when && then
        assertThrows(DataIntegrityViolationException.class, ()-> ticketService.saveTicket(ticket));

    }
}
