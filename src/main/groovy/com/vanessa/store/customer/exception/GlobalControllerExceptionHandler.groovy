package com.vanessa.store.customer.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CustomerAlreadyExistentException.class)
    void handleExistentCustomer() { }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    void handleCustomerNotFound() { }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerBadInformationException.class)
    void handleBadInformation() { }
}