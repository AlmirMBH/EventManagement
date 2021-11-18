package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.domain.Ticket;
import com.zekerijah.eventdemo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public List<Ticket> findAllTicket(){
        return ticketRepository.findAll();
    }

    @Transactional
    public Optional<Ticket> findTicket(Long id){
        return ticketRepository.findById(id);
    }

    @Transactional
    public Ticket saveTicket (Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void updateTicket (Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteTicket (Long id) {
        ticketRepository.deleteById(id);
    }

}
