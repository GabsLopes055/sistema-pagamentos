package com.sistemapagamentos.controller.Exceptions;

import com.sistemapagamentos.controller.ExampleExecption.StandartError;
import com.sistemapagamentos.services.Exceptions.EmailInUse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class ExceptionEmailInUse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailInUse.class)
    public ResponseEntity<StandartError> emailInUse(EmailInUse emailInUse, HttpServletRequest request){

        StandartError error = new StandartError();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("O e-mail fornecido já está em uso. Por favor, escolha outro e-mail.");
        error.setMessage(emailInUse.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

}
