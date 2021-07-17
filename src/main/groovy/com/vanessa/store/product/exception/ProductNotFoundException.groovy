package com.vanessa.store.product.exception

class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(String errorMessage) {
        super(errorMessage)
    }
}
