package com.vanessa.store.customer.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.vanessa.store.product.model.Product
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Wishlist implements Serializable {
    Long customerId
    Set<Product> wishlist
}
