package com.sistemapagamentos.services.Exceptions;

public class EmailInUse extends RuntimeException{

    public EmailInUse(String message) {
        super(message);
    }

}
