package com.vanessa.store.customer.service

import static com.vanessa.store.converter.ModelEntityConverter.convertCustomerToEntity
import static com.vanessa.store.converter.ModelEntityConverter.convertCustomerToModel
import com.vanessa.store.customer.exception.CustomerAlreadyExistentException
import com.vanessa.store.customer.exception.CustomerBadInformationException
import com.vanessa.store.customer.exception.CustomerNotFoundException
import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.model.CustomerCreate
import com.vanessa.store.customer.repository.CustomerEntity
import com.vanessa.store.customer.repository.CustomerRepository

class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository

    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository
    }

    @Override
    Customer create(CustomerCreate customer) {
        List<CustomerEntity> existentCustomer = customerRepository.findByEmail(customer.email)
        if (existentCustomer) throw new CustomerAlreadyExistentException("Cliente já possui cadastro com e-mail: $customer.email")
        CustomerEntity customerToCreate = new CustomerEntity(email: customer.email, name: customer.name)
        convertCustomerToModel(customerRepository.save(customerToCreate))
    }

    @Override
    Customer update(Customer customer) {
        if (!customer.id) throw new CustomerBadInformationException("Id do cliente é obrigatório: $customer")
        Optional<CustomerEntity> existentCustomer = customerRepository.findById(customer.id)
        existentCustomer.map {
            if (customer.name) it.name = customer.name
            if (customer.email) it.email = customer.email
            convertCustomerToModel(customerRepository.save(it))
        }.orElseThrow {
            throw new CustomerNotFoundException("Não foi possível encontrar o cliente: $customer.id")
        }
    }

    @Override
    Customer updateWishList(Customer customer) {
        CustomerEntity customerToUpdate = convertCustomerToEntity(customer)
        convertCustomerToModel(customerRepository.save(customerToUpdate))
    }

    @Override
    Customer findByEmail(String email) {
        List<CustomerEntity> foundCustomer = customerRepository.findByEmail(email)
        if (!foundCustomer) throw new CustomerNotFoundException("Não foi possível encontrar o cliente: $email")
        convertCustomerToModel(foundCustomer.first())
    }

    @Override
    Customer findById(Long id) {
        customerRepository.findById(id).map{
            convertCustomerToModel(it)
        }.orElseThrow {
            new CustomerNotFoundException("Cliente não encontrado.")
        }
    }

    @Override
    List<Customer> findAll() {
        return customerRepository.findAll()
                .collect{ convertCustomerToModel(it)}
    }

    @Override
    void remove(Long id) {
        CustomerEntity entity = convertCustomerToEntity(findById(id))
        customerRepository.delete(entity)
    }

}
