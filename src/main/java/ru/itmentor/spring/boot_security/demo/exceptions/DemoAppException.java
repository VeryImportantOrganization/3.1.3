package ru.itmentor.spring.boot_security.demo.exceptions;

import org.springframework.http.HttpStatus;

public class DemoAppException extends RuntimeException {

    private final HttpStatus status;

    public DemoAppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
