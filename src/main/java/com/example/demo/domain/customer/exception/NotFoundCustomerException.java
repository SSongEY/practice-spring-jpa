package com.example.demo.domain.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Can not find customer")
public class NotFoundCustomerException extends IllegalArgumentException {
    public NotFoundCustomerException() {
    }

    public NotFoundCustomerException(String s) {
        super(s);
    }

    public NotFoundCustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundCustomerException(Throwable cause) {
        super(cause);
    }
}
