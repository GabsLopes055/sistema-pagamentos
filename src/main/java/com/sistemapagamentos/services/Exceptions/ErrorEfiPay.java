package com.sistemapagamentos.services.Exceptions;

public class ErrorEfiPay extends RuntimeException{

    public ErrorEfiPay(String error) {
        super(error);
    }
}
