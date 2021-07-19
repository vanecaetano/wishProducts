package com.vanessa.store.config

import com.vanessa.store.customer.exception.CustomerAlreadyExistentException
import com.vanessa.store.customer.exception.CustomerBadInformationException
import com.vanessa.store.customer.exception.CustomerNotFoundException
import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.wishlist.exception.WishlistBadInformationException
import groovy.transform.Canonical
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistentException)
    ResponseEntity<ErrorMessage> conflictExceptionHandler(Exception ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.CONFLICT)
    }

    @ExceptionHandler([
            CustomerNotFoundException,
            ProductNotFoundException
    ])
    ResponseEntity<ErrorMessage> notFoundExceptionHandler(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler([
            CustomerBadInformationException,
            WishlistBadInformationException
    ])
    ResponseEntity<ErrorMessage> badRequestExceptionHandler(RuntimeException e) {
        ErrorMessage errorMessage = new ErrorMessage(errorMessage: e.message)
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST)
    }

}

@Canonical
class ErrorMessage {
    String errorMessage
}