package com.zekerijah.eventdemo.controller;

import com.zekerijah.eventdemo.controller.dto.CreateVenueReq;
import com.zekerijah.eventdemo.controller.dto.CreateVenueRes;
import com.zekerijah.eventdemo.controller.dto.UpdateVenueReq;
import com.zekerijah.eventdemo.controller.dto.UpdateVenueRes;
import com.zekerijah.eventdemo.controller.mapper.VenueMapper;
import com.zekerijah.eventdemo.domain.Venue;
import com.zekerijah.eventdemo.service.VenueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j // annotation for custom messages, see log.info below
@RestController
@RequiredArgsConstructor // Lombock generated constructor with all class fields / arguments
@CrossOrigin(origins = "http://localhost:3000") // domain and port for front-end
@RequestMapping(value = "/venues") // domain mapping for the entire controller

public class VenueController {
    private final VenueService venueService;
    private final VenueMapper venueMapper;

    @GetMapping
    public List<Venue> getAllVenues(){
        return venueService.findAllVenue();
    }

    @GetMapping("/{id}")
    public Venue getVenue(@PathVariable Long id){
        return venueService.findVenue(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateVenueRes createVenue(@RequestBody @Validated CreateVenueReq req){
        // @Validated validates user input against VenueReq
        log.info("Create venue " + req.toString());
         Venue venue = venueMapper.map(req);
         Venue persisted = venueService.saveVenue(venue);
         return venueMapper.map(persisted);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdateVenueRes updateVenue(@RequestBody UpdateVenueReq req){
        log.info("Update venue: " + req.toString());
        Venue venue =  Venue.builder()
                .id(req.getId())
                .venueName(req.getVenueName())
                .addressLine1(req.getAddressLine1())
                .addressLine2(req.getAddressLine2())
                .city(req.getCity())
                .postCode(req.getPostCode())
                .build();

        Venue updated = venueService.updateVenue (venue);
        return venueMapper.mapUpdate (updated);
    }

    @DeleteMapping("/{id}")
    public void deleteVenue(@PathVariable Long id ) {
        venueService.deleteVenue(id);
    }
}
