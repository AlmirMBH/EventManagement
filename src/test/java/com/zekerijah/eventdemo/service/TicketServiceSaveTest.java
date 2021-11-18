package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
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
    void whenTicketStartSaleDateBeforeNow_thenThrowException(){
        //given
        Period period = PeriodUtil.generatePeriodWithStartDateBeforeNow();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(20.00)
                .quantityAvailable(400)
                .period(period)
                .build();

        //when && then
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> ticketService.saveTicket(ticket));

        assertThat(exception.getMessage()).isEqualTo("Start date is before start now");
    }

    @Test
    void  whenTicketEndSaleTimeBeforeStartSaleTime_thenThrowException(){
        //given
        Period period = PeriodUtil.generatePeriodWithEndTimeBeforeStartTime();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(20.00)
                .quantityAvailable(400)
                .period(period)
                .build();

        //when && then
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> ticketService.saveTicket(ticket));

        assertThat(exception.getMessage()).isEqualTo("End time is before start time");
    }

    @Test
    void whenTicketStartSaleTimeBeforeNow_thenThrowException(){
        //given
        Period period = PeriodUtil.generatePeriodWithStartTimeBeforeNow();

        Ticket ticket = Ticket.builder()
                .name("Dummy name")
                .price(20.00)
                .period(period)
                .quantityAvailable(250)
                .build();

        //when && then
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> ticketService.saveTicket(ticket));

        assertThat(exception.getMessage()).isEqualTo("End time is before start time");
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
