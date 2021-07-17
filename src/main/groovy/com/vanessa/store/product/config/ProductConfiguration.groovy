package com.vanessa.store.product.config

import com.vanessa.store.product.repository.ProductService
import com.vanessa.store.product.repository.ProductRepository
import com.vanessa.store.product.service.ProductServiceImp
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ProductConfiguration {
    @Value('${luizalabs.product.api.url}')
    String productApiUrl

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build()
    }

    @Bean
    ProductRepository productRepository(RestTemplate restTemplate) {
        new ProductRepository(restTemplate, productApiUrl)
    }

    @Bean
    ProductService productService(ProductRepository productRepository) {
        new ProductServiceImp(productRepository)
    }
}
