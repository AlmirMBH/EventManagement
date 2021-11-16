package com.zekerijah.eventdemo.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAllTicket(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findTicket(Integer id){
        return ticketRepository.findById(id);
    }

    public void saveTicket (Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void updateTicket (Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteTicket (Integer id) {
        ticketRepository.deleteById(id);
    }

}
