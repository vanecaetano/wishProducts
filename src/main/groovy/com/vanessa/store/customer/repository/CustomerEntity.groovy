package com.vanessa.store.customer.repository

import groovy.transform.Canonical

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name="customers")
@Entity
@Canonical
class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id
    @Column(name = "email")
    String email
    @Column(name = "name")
    String name
}
