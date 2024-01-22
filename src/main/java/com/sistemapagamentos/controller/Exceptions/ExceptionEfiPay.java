package com.sistemapagamentos.controller.Exceptions;

import com.sistemapagamentos.controller.ExampleExecption.StandartError;
import com.sistemapagamentos.services.Exceptions.ErrorEfiPay;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionEfiPay extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorEfiPay.class)
    public ResponseEntity<StandartError> efiPayException(ErrorEfiPay error, HttpServletRequest request) {

        StandartError errorEfiPay = new StandartError();

        errorEfiPay.setTimestamp(Instant.now());
        errorEfiPay.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorEfiPay.setError(errorEfiPay.getError());
        errorEfiPay.setMessage(errorEfiPay.getMessage());
        errorEfiPay.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorEfiPay);

    }

}
