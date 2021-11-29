package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketReq {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    @Positive
    private Integer quantityAvailable;
    @NotNull
    private PeriodDto period;

    @Override
    public String toString() {
        return "UpdateTicketReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantityAvailable=" + quantityAvailable +
                ", period=" + period +
                '}';
    }
}
