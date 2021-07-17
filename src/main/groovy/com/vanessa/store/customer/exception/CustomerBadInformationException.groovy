package com.vanessa.store.customer.exception

class CustomerBadInformationException extends RuntimeException {
    CustomerBadInformationException(String errorMessage) {
        super(errorMessage)
    }
}
