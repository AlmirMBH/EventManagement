package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.CreateTicketReq;
import com.zekerijah.eventdemo.controller.dto.CreateTicketRes;
import com.zekerijah.eventdemo.controller.dto.UpdateTicketReq;
import com.zekerijah.eventdemo.controller.mapper.PeriodMapper;
import com.zekerijah.eventdemo.controller.mapper.TicketMapper;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import com.zekerijah.eventdemo.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final PeriodMapper periodMapper;
    private final TicketMapper ticketMapper;

    @GetMapping()
    public List<Ticket> getAllTickets(){
        return ticketService.findAllTicket();
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id){
        return ticketService.findTicket(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTicketRes createTicket(@RequestBody @Validated CreateTicketReq req){
        log.info("Create ticket " + req.toString());
        Period period = periodMapper.map(req.getPeriod());
        Ticket ticket = ticketMapper.map(req, period);
        Ticket persisted = ticketService.saveTicket(ticket);
        return ticketMapper.map(persisted);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateTicket(@RequestBody UpdateTicketReq req){
        Period period = periodMapper.map(req.getPeriod());

        Ticket ticket = Ticket.builder()
                .name(req.getName())
                .price(req.getPrice())
                .quantityAvailable(req.getQuantityAvailable())
                .period(period)
                .build();

        ticketService.updateTicket(ticket);

    }

    @DeleteMapping("{id}/delete")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

}
