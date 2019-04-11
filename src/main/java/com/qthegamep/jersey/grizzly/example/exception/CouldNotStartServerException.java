package com.qthegamep.jersey.grizzly.example.exception;

public class CouldNotStartServerException extends RuntimeException {

    public CouldNotStartServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
