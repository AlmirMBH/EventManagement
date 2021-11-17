package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.IntegrationTest;
import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
                .quantityAvailabel(300)
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
                .quantityAvailabel(400)
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
                .quantityAvailabel(400)
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
                .quantityAvailabel(400)
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
                .quantityAvailabel(250)
                .build();

        //when && then
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> ticketService.saveTicket(ticket));

        assertThat(exception.getMessage()).isEqualTo("End time is before start time");
}
