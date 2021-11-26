package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.CreateEventDto;
import com.zekerijah.eventdemo.controller.dto.EventCreatedResDto;
import com.zekerijah.eventdemo.controller.dto.UpdateEventDto;
import com.zekerijah.eventdemo.controller.mapper.EventMapper;
import com.zekerijah.eventdemo.controller.mapper.PeriodMapper;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.Period;
import com.zekerijah.eventdemo.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/events")
public class EventController {

    private final EventService eventService;
    private final PeriodMapper periodMapper;
    private final EventMapper eventMapper;

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.findAllEvent();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id){
        return eventService.findEvent(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventCreatedResDto createEvent(@RequestBody @Validated CreateEventDto req){
        log.info("Create event " + req.toString());
        Period period = periodMapper.map( req.getPeriod() );
        Event event = eventMapper.map(req, period);
        Event persisted = eventService.saveEvent(event);
        return eventMapper.map(persisted);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateEvent(@RequestBody UpdateEventDto req){
        Period period = periodMapper.map( req.getPeriod());

        Event event = Event.builder()
                .id(req.getId())
                .title(req.getTitle())
                .description(req.getDescription())
                .period(period)
                .tickets(Collections.emptyList())
                .build();

        eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id ) {
        eventService.deleteEvent(id);
    }

}
