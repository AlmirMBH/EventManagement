package com.zekerijah.eventdemo.controller.handler;

import lombok.*;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Data
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class ApiError {
    private int code;
    private String message;
    private List<ViolationError> violationErrors;
}
