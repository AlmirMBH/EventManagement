package com.zekerijah.eventdemo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getAllEvents(){
        return eventService.findAllEvent();
    }

    @RequestMapping(value = "/events/{id}")
    public Optional<Event> getEvent(@PathVariable Integer id){
        return eventService.findEvent(id);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody Event event){
        eventService.saveEvent(event);
    }

    @RequestMapping(value = "/events/{id}/edit", method = RequestMethod.PUT)
    public void updateEvent(@PathVariable Integer id, @RequestBody Event event){
        eventService.updateEvent(event);
    }

    @RequestMapping(value = "/events/{id}/delete", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
    }

}
