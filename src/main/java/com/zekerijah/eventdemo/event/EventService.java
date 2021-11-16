package com.zekerijah.eventdemo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAllEvent(){
        return eventRepository.findAll();
    }

    public Optional<Event> findEvent(Integer id){
        return eventRepository.findById(id);
    }

    public Event saveEvent (Event event){
        eventRepository.save(event);
        return event;
    }

    public void updateEvent (Event event) {
        eventRepository.save(event);
    }

    public void deleteEvent (Integer id) {
        eventRepository.deleteById(id);
    }
}
