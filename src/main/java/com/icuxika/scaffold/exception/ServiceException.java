package com.icuxika.scaffold.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    private Integer statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
