package com.zekerijah.eventdemo;

import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.domain.Ticket;

public class TicketUtil {

    public static Ticket generate() {

        Period period = PeriodUtil.generate();

        return Ticket.builder()
                .name("Dummy Name")
                .price(20.00)
                .quantityAvailable(500)
                .period(period)
                .build();
    }
}
