package com.vanessa.store.customer

import com.vanessa.store.customer.controller.CustomerController
import com.vanessa.store.customer.exception.CustomerAlreadyExistentException
import com.vanessa.store.customer.exception.CustomerNotFoundException
import com.vanessa.store.customer.model.Customer
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@SpringBootApplication
@RunWith(SpringRunner.class)
@AutoConfigureJdbc
@AutoConfigureDataJpa
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerControllerTest {

    @Autowired
    CustomerController customerController
    Customer joao = new Customer(name: "João", email: "joao@email.com")
    Customer maria = new Customer(name: "Maria", email: "maria@email.com")

    @Test
    void returnAllCustomers() {
        customerController.create(joao)
        customerController.create(maria)
        ResponseEntity responseEntity = customerController.list()
        assert responseEntity.body?.size() == 2
    }

    @Test
    void createCustomerSuccessfully() {
        ResponseEntity responseEntity = customerController.create(joao)
        assert responseEntity.statusCode == HttpStatus.CREATED
    }

    @Test
    void raiseConflictIfCreateUserWithEmailAlreadyExistent() {
        customerController.create(joao)
        Assert.assertThrows(CustomerAlreadyExistentException,
                ()-> {
                    customerController.create(joao)
                });
    }

    @Test
    void findExistentUserByIdSuccessfully() {
        ResponseEntity customerCreated = customerController.create(joao)
        ResponseEntity responseEntity = customerController
                .findById(customerCreated.body?.id)
        assert responseEntity.statusCode == HttpStatus.OK
    }

    @Test
    void findExistentUserByEmailSuccessfully() {
        customerController.create(joao)
        ResponseEntity<Customer> customerFound = customerController
                .findByEmail(joao.email)
        assert customerFound.statusCode == HttpStatus.OK
    }

    @Test
    void raiseNotFoundWhenInexistentEmailOnDatabase() {
        Assert.assertThrows(CustomerNotFoundException,
                ()-> {
                    ResponseEntity<Customer> customer = customerController
                            .findByEmail("inexistent@email.com")
                });
    }

    @Test
    void shouldDeleteCustomerSuccessfully() {
        ResponseEntity responseEntity = customerController.create(joao)
        customerController.remove(responseEntity.body?.id)
    }
}
