package com.cooksy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException e) {
        HttpStatus paymentRequired = HttpStatus.PAYMENT_REQUIRED;

        ApiException apiException = new ApiException(e.getMessage(),
                e,
                paymentRequired,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, paymentRequired);
    }
}
