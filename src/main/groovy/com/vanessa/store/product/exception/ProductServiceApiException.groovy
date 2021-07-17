package com.vanessa.store.product.exception

class ProductServiceApiException extends RuntimeException {
    ProductServiceApiException(String errorMessage) {
        super(errorMessage)
    }
}
