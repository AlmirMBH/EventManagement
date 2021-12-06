package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.controller.dto.CreateEventReq;
import com.zekerijah.eventdemo.controller.dto.CreateEventRes;
import com.zekerijah.eventdemo.controller.dto.UpdateEventReq;
import com.zekerijah.eventdemo.controller.dto.UpdateEventRes;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.Period;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    PeriodMapper periodMapper = new PeriodMapper();

    public CreateEventRes map(Event event) {
        return CreateEventRes.builder()
                .id(event.getId())
                .description(event.getDescription())
                .period(periodMapper.map(event.getPeriod()))
                .title(event.getTitle())
                .build();
    }

    public Event map(CreateEventReq req, Period period) {
        return Event.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .period(period)
                // .period(periodMapper.map(req.getPeriod()))
                .build();
    }

    public UpdateEventRes mapUpdate (Event event) {
        return UpdateEventRes.builder()
                .id(event.getId())
                .description(event.getDescription())
                .period(periodMapper.map(event.getPeriod()))
                .title(event.getTitle())
                .build();
    }
}
