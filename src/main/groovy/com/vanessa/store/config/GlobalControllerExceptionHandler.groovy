package com.vanessa.store.config

import com.vanessa.store.customer.exception.CustomerAlreadyExistentException
import com.vanessa.store.customer.exception.CustomerBadInformationException
import com.vanessa.store.customer.exception.CustomerNotFoundException
import groovy.transform.Canonical
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistentException)
    ResponseEntity<ErrorMessage> conflictExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), request), HttpStatus.CONFLICT)
    }

    @ExceptionHandler([
            CustomerNotFoundException,
    ])
    ResponseEntity<ErrorMessage> notFoundExceptionHandler(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), request), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler([
            CustomerBadInformationException
    ])
    ResponseEntity<ErrorMessage> badRequestExceptionHandler(CustomerBadInformationException e) {
        ErrorMessage errorMessage = new ErrorMessage(errorMessage: e.message)
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    private static ErrorMessage buildErrorDetails(String message, WebRequest request) {
        new ErrorMessage(message, request.getDescription(false))
    }

}

@Canonical
class ErrorMessage {
    String errorMessage
    String detail
}