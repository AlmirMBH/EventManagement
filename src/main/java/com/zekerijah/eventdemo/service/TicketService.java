package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.domain.Ticket;
import com.zekerijah.eventdemo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.zekerijah.eventdemo.controller.handler.ErrorCode.TICKET_NOT_FOUND;
import static com.zekerijah.eventdemo.domain.EventDemoException.exception;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public List<Ticket> findAllTicket(){
        return ticketRepository.findAll();
    }

    @Transactional
    public Ticket findTicket(Long id){
        return ticketRepository.findById(id)
                .orElseThrow(exception(TICKET_NOT_FOUND));
    }

    @Transactional
    public Ticket saveTicket (Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket updateTicket (Ticket updateTicket) {
        final Ticket ticket = findTicket(updateTicket.getId());

        ticket.setName(updateTicket.getName());
        ticket.setPrice(updateTicket.getPrice());
        ticket.setQuantityAvailable(updateTicket.getQuantityAvailable());
        ticket.setPeriod(updateTicket.getPeriod());

        return ticketRepository.save(ticket);

//        currentTicket.setName(ticket.getName());
//        currentTicket.setPrice(ticket.getPrice());
//        currentTicket.setQuantityAvailable(ticket.getQuantityAvailable());
//        currentTicket.getPeriod().setStartDate(ticket.getPeriod().getStartDate());
//        currentTicket.getPeriod().setEndDate(ticket.getPeriod().getEndDate());
//        currentTicket.getPeriod().setStartTime(ticket.getPeriod().getStartTime());
//        currentTicket.getPeriod().setEndTime(ticket.getPeriod().getEndTime());

    }

    @Transactional
    public void deleteTicket (Long id) {
        ticketRepository.deleteById(id);
    }

}
