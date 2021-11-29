package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.TicketUtil;
import com.zekerijah.eventdemo.controller.dto.CreateTicketRes;
import com.zekerijah.eventdemo.domain.Ticket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketMapperTest {

    TicketMapper ticketMapper = new TicketMapper();
    PeriodMapper periodMapper = new PeriodMapper();

    @Test
    void whenValidRequest_thanMap(){
        //given
        Ticket generate = TicketUtil.generate();
        //when
        CreateTicketRes res = ticketMapper.map(generate);
        //then
        assertThat(res).isNotNull();
        assertThat(generate.getId()).isEqualTo(res.getId());
        assertThat(generate.getName()).isEqualTo(res.getName());
        assertThat(generate.getPrice()).isEqualTo(res.getPrice());
        assertThat(generate.getQuantityAvailable()).isEqualTo(res.getQuantityAvailable());
        assertThat(generate.getPeriod()).isEqualTo(periodMapper.map(res.getPeriod()));
    }
}
