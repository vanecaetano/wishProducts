package com.vanessa.store.customer.model

import groovy.transform.Canonical

@Canonical
class Customer implements Serializable {
    Long id
    String email
    String name
}
