package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.controller.dto.CreateEventDto;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Target;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.util.List;
import java.util.Optional;

import static com.zekerijah.eventdemo.controller.handler.ErrorCode.EVENT_NOT_FOUND;
import static com.zekerijah.eventdemo.domain.EventDemoException.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<Event> findAllEvent(){
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Event findEvent(Long id){
        return eventRepository.findById(id)
                .orElseThrow(exception(EVENT_NOT_FOUND));
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
    public void deleteEvent (Long id) {
        eventRepository.deleteById(id);
    }
}
