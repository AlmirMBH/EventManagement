package com.zekerijah.eventdemo.controller.handler;

import com.zekerijah.eventdemo.domain.EventDemoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

import static com.zekerijah.eventdemo.controller.handler.ErrorCode.*;
import static java.util.Collections.*;

@Slf4j
@ControllerAdvice
public class EventDemoExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        return createResponseEntity(ex, INTERNAL_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> missingServletRequestParameter(MissingServletRequestParameterException ex) {
        return createResponseEntity(ex, BAD_REQUEST_CODE);
    }

    @ExceptionHandler(EventDemoException.class)
    public ResponseEntity<ApiError> handleBusinessException(EventDemoException ex) {
        return createResponseEntity(ex, ex.getErrorCode());
    }

    private ResponseEntity<ApiError> createResponseEntity(Exception ex, ErrorCode code) {
        return new ResponseEntity<ApiError>(
               ApiError.builder()
                       .code(code.getCode())
                       .message(code.getMessage())
                       .violationErrors(
                               singletonList(new ViolationError(code.getCode(), ex.getMessage()))
                       )
                       .build(), code.getStatus()
        );
    }

}
