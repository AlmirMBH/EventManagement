package com.zekerijah.eventdemo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketReq {
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantityAvailable;
    @NotNull
    private PeriodDto period;

    @Override
    public String toString() {
        return "CreateTicketReq{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantityAvailable=" + quantityAvailable +
                ", period=" + period +
                '}';
    }
}
