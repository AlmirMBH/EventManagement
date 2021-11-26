package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.CreateTicketDto;
import com.zekerijah.eventdemo.controller.dto.UpdateTicketDto;
import com.zekerijah.eventdemo.controller.mapper.PeriodMapper;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import com.zekerijah.eventdemo.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {


    private final TicketService ticketService;
    private final PeriodMapper periodMapper;

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets(){
        return ticketService.findAllTicket();
    }

    @RequestMapping(value = "/tickets/{id}")
    public Ticket getTicket(@PathVariable Long id){
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

    @PutMapping("/tickets")
    public void updateTicket(@RequestBody UpdateTicketDto req){
        Period period = periodMapper.map(req.getPeriod());

        Ticket ticket = Ticket.builder()
                .name(req.getName())
                .price(req.getPrice())
                .quantityAvailable(req.getQuantityAvailable())
                .period(period)
                .build();

        ticketService.updateTicket(ticket);

    }

    @DeleteMapping("/tickets/{id}/delete")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

}
