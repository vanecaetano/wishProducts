package com.vanessa.store.customer

import com.vanessa.store.customer.controller.CustomerController
import com.vanessa.store.customer.exception.CustomerAlreadyExistentException
import com.vanessa.store.customer.exception.CustomerNotFoundException
import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.model.CustomerCreate
import com.vanessa.store.customer.repository.CustomerEntity
import com.vanessa.store.customer.repository.CustomerRepository
import com.vanessa.store.customer.service.CustomerService
import com.vanessa.store.customer.service.CustomerServiceImpl
import com.vanessa.store.product.repository.ProductRepository
import com.vanessa.store.product.service.ProductService
import com.vanessa.store.wishlist.service.WishlistServiceImpl
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
class CustomerControllerTest {
    CustomerRepository customerRepository = mock(CustomerRepository)
    ProductService productService = mock(ProductService)
    CustomerService customerService = new CustomerServiceImpl(customerRepository)
    ProductRepository productRepository = mock(ProductRepository)
    WishlistServiceImpl wishlistService = new WishlistServiceImpl(customerService, productService)
    CustomerController customerController = new CustomerController(customerService, productService, wishlistService)
    CustomerEntity joao = new CustomerEntity(id: 1, email: "joao@email.com", name: "João")

    @Test
    void returnAllCustomers() {
        when(customerRepository.findAll()).thenReturn(mockCustomers(2))
        ResponseEntity responseEntity = customerController.list()
        assert responseEntity.body?.size() == 2
    }

    @Test
    void createCustomerSuccessfully() {
        CustomerCreate maria = new CustomerCreate(email: "maria@email.com", name: "Maria")
        when(customerRepository.save(any(CustomerEntity))).thenReturn(new CustomerEntity(id: 1, email: maria.email, name:maria.name))
        ResponseEntity responseEntity = customerController.create(maria)
        assert responseEntity.statusCode == HttpStatus.CREATED
    }

    @Test
    void raiseConflictIfCreateUserWithEmailAlreadyExistent() {
        CustomerCreate joaoModel = new CustomerCreate(email: "joao@email.com", name: "Joao")
        when(customerRepository.findByEmail(joao.email)).thenReturn([joao])

        Assert.assertThrows(CustomerAlreadyExistentException,
                ()-> {
                    customerController.create(joaoModel)
                });
    }

    @Test
    void findExistentUserByIdSuccessfully() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(joao))
        ResponseEntity responseEntity = customerController
                .findById(1)
        assert responseEntity.statusCode == HttpStatus.OK
    }

    @Test
    void findExistentUserByEmailSuccessfully() {
        when(customerRepository.findByEmail(joao.email)).thenReturn([joao])
        ResponseEntity<Customer> customerFound = customerController
                .findByEmail(joao.email)
        assert customerFound.statusCode == HttpStatus.OK
    }

    @Test
    void raiseNotFoundWhenInexistentEmailOnDatabase() {
        when(customerRepository.findByEmail(joao.email)).thenReturn([])
        Assert.assertThrows(CustomerNotFoundException,
                ()-> {
                    ResponseEntity<Customer> customer = customerController
                            .findByEmail("inexistent@email.com")
                });
    }

    @Test
    void shouldDeleteCustomerSuccessfully() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(joao))
        customerController.remove(1)
    }

    private List<CustomerEntity> mockCustomers(int howMany) {
        (1..howMany).collect {
            new CustomerEntity(id: it, email: "email$it@email.com", name: "Name $it")
        }
    }
}
