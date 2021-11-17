package com.zekerijah.eventdemo.controller.dto;

import javax.validation.constraints.NotBlank;

public class CreateEventDto {
    @NotBlank
    private String title;
}
