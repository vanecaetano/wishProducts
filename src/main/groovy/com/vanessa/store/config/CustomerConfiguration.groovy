package com.vanessa.store.config

import com.vanessa.store.customer.repository.CustomerRepository
import com.vanessa.store.customer.service.CustomerService
import com.vanessa.store.customer.service.CustomerServiceImpl
import com.vanessa.store.product.service.ProductService
import com.vanessa.store.wishlist.service.WishlistService
import com.vanessa.store.wishlist.service.WishlistServiceImpl
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

    @Bean
    WishlistService wishlistService(CustomerService customerService,
                                    ProductService productService) {
        new WishlistServiceImpl(customerService, productService)
    }

}
