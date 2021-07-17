package com.vanessa.store.customer.model

import groovy.transform.Canonical

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Canonical
class CustomerCreate implements Serializable {
    @Email(message = "Informe um email válido")
    @NotBlank(message = "Informe o email")
    String email
    @NotBlank(message = "Informe o nome")
    String name
}
