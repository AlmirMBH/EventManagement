package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.controller.dto.CreateEventDto;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.EventDemoException;
import com.zekerijah.eventdemo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.zekerijah.eventdemo.controller.handler.ErrorCode.EVENT_NOT_FOUND;
import static com.zekerijah.eventdemo.domain.EventDemoException.*;

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
    public void updateEvent (Long eventId, Event event) {
        Event currentEvent = eventRepository.findById(eventId).orElseThrow(exception(EVENT_NOT_FOUND));

        currentEvent.setTitle(event.getTitle());
        currentEvent.setDescription(event.getDescription());
        currentEvent.getPeriod().setStartDate(event.getPeriod().getStartDate());
        currentEvent.getPeriod().setEndDate(event.getPeriod().getEndDate());
        currentEvent.getPeriod().setStartTime(event.getPeriod().getStartTime());
        currentEvent.getPeriod().setEndTime(event.getPeriod().getEndTime());
    }

    @Transactional
    public void deleteEvent (Long id) {
        eventRepository.deleteById(id);
    }
}
