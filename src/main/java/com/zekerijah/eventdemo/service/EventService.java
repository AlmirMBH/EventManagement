package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<Event> findAllEvent(){
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Event> findEvent(Integer id){
        return eventRepository.findById(id);
    }

    @Transactional
    public Event saveEvent (Event event){
        return eventRepository.save(event);
    }

    @Transactional
    public void updateEvent (Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent (Integer id) {
        eventRepository.deleteById(id);
    }
}
