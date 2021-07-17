package com.vanessa.store.customer.exception

class CustomerNotFoundException extends RuntimeException {
    CustomerNotFoundException(String errorMessage) {
        super(errorMessage)
    }
}
