package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.CreateTicketDto;
import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import com.zekerijah.eventdemo.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {


    private final TicketService ticketService;

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets(){
        return ticketService.findAllTicket();
    }

    @RequestMapping(value = "/tickets/{id}")
    public Optional<Ticket> getTicket(@PathVariable Long id){
        return ticketService.findTicket(id);
    }

    @PostMapping("/tickets")
    public void createTicket(@RequestBody @Validated CreateTicketDto req){
        log.info("Create ticket " + req.toString());

        Period period = Period.builder()
                .startDate(req.getPeriod().getStart().toLocalDate())
                .endDate(req.getPeriod().getEnd().toLocalDate())
                .startTime(Time.valueOf(req.getPeriod().getStart().toLocalTime()))
                .endTime(Time.valueOf(req.getPeriod().getEnd().toLocalTime()))
                .build();

        Ticket ticket = Ticket.builder()
                .name(req.getName())
                .price(req.getPrice())
                .quantityAvailable(req.getQuantityAvailable())
                .period(period)
                .build();

        ticketService.saveTicket(ticket);
    }

    @PutMapping("/tickets/{id}/edit")
    public void updateTicket(@PathVariable Long id, @RequestBody CreateTicketDto req){
        Period period = Period.builder()
                .startDate(req.getPeriod().getStart().toLocalDate())
                .endDate(req.getPeriod().getEnd().toLocalDate())
                .startTime(Time.valueOf(req.getPeriod().getStart().toLocalTime()))
                .endTime(Time.valueOf(req.getPeriod().getEnd().toLocalTime()))
                .build();

        Ticket ticket = Ticket.builder()
                .name(req.getName())
                .price(req.getPrice())
                .quantityAvailable(req.getQuantityAvailable())
                .period(period)
                .build();


    }

    @DeleteMapping("/tickets/{id}/delete")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

}
