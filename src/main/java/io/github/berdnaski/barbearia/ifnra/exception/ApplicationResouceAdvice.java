package io.github.berdnaski.barbearia.ifnra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationResouceAdvice {
    @ExceptionHandler(BarberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBarberException(BarberException e) {
        return new ApiError(e.getMessage());
    }
}
