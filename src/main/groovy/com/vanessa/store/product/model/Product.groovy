package com.vanessa.store.product.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Product {
    String id
    String title
    String image
    BigDecimal price
    String reviewScore
}
