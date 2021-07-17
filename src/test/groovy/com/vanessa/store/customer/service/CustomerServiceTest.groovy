package com.vanessa.store.customer.service

import com.vanessa.store.customer.exception.CustomerBadInformationException
import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.repository.CustomerRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CustomerServiceTest {
    CustomerService customerService

    @BeforeAll
    before() {
        customerService = new CustomerServiceImpl(Mockito.mock(CustomerRepository))
    }

    @Test
    validEmailTest() {
        Assertions.assertThrows(CustomerBadInformationException,
                ()-> {
                    customerService.create(
                            new Customer(email: "invalid", name: "João"))
                });
    }
}
