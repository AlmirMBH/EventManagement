package com.zekerijah.eventdemo.domain;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)

public class Venue {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String venueName;

    // nullable by default
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;
        Venue venue = (Venue) o;
        return getId().equals(venue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
