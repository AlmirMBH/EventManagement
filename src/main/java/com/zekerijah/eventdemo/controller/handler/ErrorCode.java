package com.zekerijah.eventdemo.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST_CODE(40000, "Bad request", BAD_REQUEST),

    //
    EVENT_NOT_FOUND(40001, "Event not found", BAD_REQUEST),

    END_DATE_IS_BEFORE_START_DATE(40002, "End date is before start date", BAD_REQUEST),

    END_TIME_IS_BEFORE_START_TIME(40002, "End time is before start time", BAD_REQUEST),

    START_DATE_IS_BEFORE_NOW(40003, "Start date is before start now", BAD_REQUEST),

    START_TIME_IS_BEFORE_NOW(40004, "End time is before start now", BAD_REQUEST),

    //

    EXCEPTION(50001, "Exception occurred", INTERNAL_SERVER_ERROR),
    INTERNAL_ERROR(50002, "Internal server error", INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus status;
}
