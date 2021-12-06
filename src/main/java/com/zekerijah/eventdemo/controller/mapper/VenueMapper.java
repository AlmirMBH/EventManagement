package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.controller.dto.CreateVenueReq;
import com.zekerijah.eventdemo.controller.dto.CreateVenueRes;
import com.zekerijah.eventdemo.controller.dto.UpdateVenueRes;
import com.zekerijah.eventdemo.domain.Venue;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

    public CreateVenueRes map(Venue venue) {
        return CreateVenueRes.builder()
                .id(venue.getId())
                .venueName(venue.getVenueName())
                .addressLine1(venue.getAddressLine1())
                .addressLine1(venue.getAddressLine2())
                .city(venue.getCity())
                .postCode(venue.getPostCode())
                .build();
    }

    public Venue map(CreateVenueReq req) {
        return Venue.builder()
                .venueName(req.getVenueName())
                .addressLine1(req.getAddressLine1())
                .addressLine1(req.getAddressLine2())
                .city(req.getCity())
                .postCode(req.getPostCode())
                .build();
    }

    public UpdateVenueRes mapUpdate (Venue venue) {
        return UpdateVenueRes.builder()
                .id(venue.getId())
                .venueName(venue.getVenueName())
                .addressLine1(venue.getAddressLine1())
                .addressLine1(venue.getAddressLine2())
                .city(venue.getCity())
                .postCode(venue.getPostCode())
                .build();
    }
}
