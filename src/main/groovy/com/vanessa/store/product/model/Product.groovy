package com.vanessa.store.product.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.Canonical

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
class Product {
    String id
    String title
    String image
    BigDecimal price
    String reviewScore
}
