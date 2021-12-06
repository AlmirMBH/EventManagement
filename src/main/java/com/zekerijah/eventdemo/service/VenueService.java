package com.zekerijah.eventdemo.service;

import com.zekerijah.eventdemo.domain.Venue;
import com.zekerijah.eventdemo.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static com.zekerijah.eventdemo.controller.handler.ErrorCode.VENUE_NOT_FOUND;
import static com.zekerijah.eventdemo.domain.EventDemoException.exception;

@Service
@RequiredArgsConstructor // instead of @Autowired from Lombock

public class VenueService {
    @Autowired // Check why it cannot work without this annotation
    private VenueRepository venueRepository;

    @Transactional(readOnly = true)
    public List<Venue> findAllVenue(){
        return venueRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Venue findVenue(Long id){
        return venueRepository.findById(id)
                .orElseThrow(exception(VENUE_NOT_FOUND));
    }

    @Transactional
    public Venue saveVenue(Venue venue){
        return venueRepository.save(venue);
    }

    @Transactional
    public Venue updateVenue (Venue updateVenue) {
        final Venue venue = findVenue(updateVenue.getId());
        venue.setVenueName(updateVenue.getVenueName());
        venue.setAddressLine1(updateVenue.getAddressLine1());
        venue.setAddressLine2(updateVenue.getAddressLine2());
        venue.setCity(updateVenue.getCity());
        venue.setPostCode(updateVenue.getPostCode());
        return venueRepository.save(venue);
    }

    @Transactional
    public void deleteVenue (Long id) {
        venueRepository.deleteById(id);
    }
}
