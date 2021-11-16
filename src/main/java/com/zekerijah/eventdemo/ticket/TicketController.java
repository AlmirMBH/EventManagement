package com.zekerijah.eventdemo.ticket;

import com.zekerijah.eventdemo.event.Event;
import com.zekerijah.eventdemo.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets(){
        return ticketService.findAllTicket();
    }

    @RequestMapping(value = "/tickets/{id}")
    public Optional<Ticket> getTicket(@PathVariable Integer id){
        return ticketService.findTicket(id);
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public void createTicket(@RequestBody Ticket ticket){
        ticketService.saveTicket(ticket);
    }

    @RequestMapping(value = "/tickets/{id}/edit", method = RequestMethod.PUT)
    public void updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket){
        ticketService.updateTicket(ticket);
    }

    @RequestMapping(value = "/tickets/{id}/delete", method = RequestMethod.DELETE)
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }

}
