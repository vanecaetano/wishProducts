package com.vanessa.store.customer.service

import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.model.CustomerCreate

interface CustomerService {

    Customer create(CustomerCreate customer)
    Customer update(Customer customer)
    Customer findByEmail(String email)
    Customer findById(Long id)
    List<Customer> findAll()
    void remove(Long id)
}
