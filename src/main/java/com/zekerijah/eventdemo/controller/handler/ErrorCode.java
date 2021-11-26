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

    PRESENT_DATE_END_TIME_IS_BEFORE_START_TIME(40003, "In present date end time is before start time", BAD_REQUEST),

    DATE_CAN_NOT_BE_EMPTY(40004, "Date and time can not be empty", BAD_REQUEST),

    TICKET_NOT_FOUND(40005, "Ticket not found", BAD_REQUEST),
    //

    EXCEPTION(50001, "Exception occurred", INTERNAL_SERVER_ERROR),
    INTERNAL_ERROR(50002, "Internal server error", INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus status;
}
