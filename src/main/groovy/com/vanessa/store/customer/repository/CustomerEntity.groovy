package com.vanessa.store.customer.repository

import com.vanessa.store.product.repository.ProductEntity
import groovy.transform.Canonical

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<ProductEntity> wishlist
}
