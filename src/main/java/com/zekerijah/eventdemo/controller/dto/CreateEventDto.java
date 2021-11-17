package com.zekerijah.eventdemo.controller.dto;

import com.zekerijah.eventdemo.domain.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Period period;
}
