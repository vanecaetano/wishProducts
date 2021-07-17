package com.vanessa.store.customer.exception

class CustomerAlreadyExistentException extends RuntimeException {
    CustomerAlreadyExistentException(String errorMessage) {
        super(errorMessage)
    }
}
