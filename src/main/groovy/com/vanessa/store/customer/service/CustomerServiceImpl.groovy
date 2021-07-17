package com.vanessa.store.customer.service

import com.vanessa.store.customer.exception.CustomerAlreadyExistentException
import com.vanessa.store.customer.exception.CustomerBadInformationException
import com.vanessa.store.customer.exception.CustomerNotFoundException
import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.repository.CustomerEntity
import com.vanessa.store.customer.repository.CustomerRepository

class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository

    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository
    }

    @Override
    Customer create(Customer customer) {
        List<CustomerEntity> existentCustomer = customerRepository.findByEmail(customer.email)
        if (existentCustomer) throw new CustomerAlreadyExistentException("Cliente já possui cadastro com e-mail: $customer.email")
        CustomerEntity customerToCreate = new CustomerEntity(email: customer.email, name: customer.name)
        convert(customerRepository.save(customerToCreate))
    }

    @Override
    Customer update(Customer customer) {
        if (!customer.id) throw new CustomerBadInformationException("Id do cliente é obrigatório: $customer")
        Optional<CustomerEntity> existentCustomer = customerRepository.findById(customer.id)
        existentCustomer.map {
            CustomerEntity customerToUpdate = convert(customer)
            convert(customerRepository.save(customerToUpdate))
        }.orElseThrow {
            throw new CustomerNotFoundException("Não foi possível encontrar o cliente: $customer.id")
        }
    }

    @Override
    Customer findByEmail(String email) {
        List<CustomerEntity> foundCustomer = customerRepository.findByEmail(email)
        if (!foundCustomer) throw new CustomerNotFoundException("Não foi possível encontrar o cliente: $email")
        convert(foundCustomer.first())
    }

    @Override
    Customer findById(Long id) {
        customerRepository.findById(id).map{
            convert(it)
        }.orElseThrow {
            new CustomerNotFoundException("Cliente não encontrado.")
        }
    }

    @Override
    List<Customer> findAll() {
        return customerRepository.findAll()
                .collect{
            convert(it)
        }
    }

    @Override
    void remove(Long id) {
        CustomerEntity entity = convert(findById(id))
        customerRepository.delete(entity)
    }

    static CustomerEntity convert(Customer customer) {
        new CustomerEntity(
                id: customer.id,
                email: customer.email,
                name: customer.name
        )
    }

    static Customer convert(CustomerEntity customer) {
        new Customer (
                id: customer.id,
                email: customer.email,
                name: customer.name
        )
    }

    boolean isValidEmailFormat(String email) {
        email?: email.contains("@")
    }
}
