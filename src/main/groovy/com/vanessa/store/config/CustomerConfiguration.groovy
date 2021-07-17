package com.vanessa.store.config

import com.vanessa.store.customer.repository.CustomerRepository
import com.vanessa.store.customer.service.CustomerService
import com.vanessa.store.customer.service.CustomerServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories
class CustomerConfiguration {

    @Bean
    CustomerService customerService(CustomerRepository customerRepository) {
        new CustomerServiceImpl(customerRepository)
    }

}
