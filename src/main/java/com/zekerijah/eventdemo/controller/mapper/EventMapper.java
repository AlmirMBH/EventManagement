package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.controller.dto.CreateEventDto;
import com.zekerijah.eventdemo.controller.dto.EventCreatedResDto;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.Period;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class EventMapper {

    public EventCreatedResDto map(Event event) {
        return EventCreatedResDto
                .builder()
                .id(event.getId())
                .title(event.getTitle())
                .build();
    }

    public Event map(CreateEventDto req, Period period) {
        return Event.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .period(period)
                .build();
    }

}
