package com.vanessa.store.customer.model

import groovy.transform.Canonical

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Canonical
class Customer implements Serializable {
    Long id
    String email
    String name
}
