package com.zekerijah.eventdemo.domain;

import com.zekerijah.eventdemo.controller.handler.ErrorCode;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class EventDemoException extends RuntimeException{
    private final ErrorCode errorCode;

    public EventDemoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static Supplier<EventDemoException> exception(ErrorCode errorCode) {
        return () -> new EventDemoException(errorCode);
    }
}
