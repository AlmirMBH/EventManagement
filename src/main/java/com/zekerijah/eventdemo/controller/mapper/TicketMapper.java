package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.controller.dto.CreateEventReq;
import com.zekerijah.eventdemo.controller.dto.CreateTicketReq;
import com.zekerijah.eventdemo.controller.dto.CreateTicketRes;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    PeriodMapper periodMapper = new PeriodMapper();

    public CreateTicketRes map(Ticket ticket){
        return CreateTicketRes.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .price(ticket.getPrice())
                .quantityAvailable(ticket.getQuantityAvailable())
                .period(periodMapper.map(ticket.getPeriod()))
                .build();
    }

    public Ticket map(CreateTicketReq req, Period period){
        return Ticket.builder()
                .name(req.getName())
                .price(req.getPrice())
                .quantityAvailable(req.getQuantityAvailable())
                .period(period)
                .build();
    }
}
