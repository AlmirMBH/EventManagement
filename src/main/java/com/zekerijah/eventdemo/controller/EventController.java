package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.CreateEventDto;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public List<Event> getAllEvents(){
        return eventService.findAllEvent();
    }

    @RequestMapping(value = "/events/{id}")
    public Event getEvent(@PathVariable Long id){
        return eventService.findEvent(id);
    }

    @PostMapping(value = "/events")
    public void createEvent(@RequestBody CreateEventDto req){
        Event event = Event.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .build();
        eventService.saveEvent(event);
    }

    @RequestMapping(value = "/events/{id}/edit", method = RequestMethod.PUT)
    public void updateEvent(@PathVariable Integer id, @RequestBody Event event){
        eventService.updateEvent(event);
    }

    @RequestMapping(value = "/events/{id}/delete", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

}
