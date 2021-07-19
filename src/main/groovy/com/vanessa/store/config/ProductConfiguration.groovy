package com.vanessa.store.config

import com.vanessa.store.product.repository.ProductRepository
import com.vanessa.store.product.service.ProductService
import com.vanessa.store.product.repository.ProductApiClient
import com.vanessa.store.product.service.ProductServiceImp
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.client.RestTemplate

@Configuration
@EnableJpaRepositories
class ProductConfiguration {
    @Value('${luizalabs.product.api.url}')
    String productApiUrl

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build()
    }

    @Bean
    ProductApiClient productApiClient(RestTemplate restTemplate) {
        new ProductApiClient(restTemplate, productApiUrl)
    }

    @Bean
    ProductService productService(ProductRepository productRepository, ProductApiClient productApiClient) {
        new ProductServiceImp(productRepository, productApiClient)
    }
}
