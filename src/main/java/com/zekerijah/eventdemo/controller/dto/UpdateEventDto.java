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
public class UpdateEventDto {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private PeriodDto period;

    @Override
    public String toString() {
        return "UpdateEventDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", period=" + period +
                '}';
    }
}
