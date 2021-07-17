package com.vanessa.store.product.repository

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.product.exception.ProductServiceApiException
import groovy.transform.Canonical
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Canonical
class ProductApiClient {

    RestTemplate restTemplate
    String productApiUrl

    ProductApiClient(RestTemplate restTemplate, String productApiUrl) {
        this.restTemplate = restTemplate
        this.productApiUrl = productApiUrl
    }

    Optional<ProductEntity> findProductById(String id) {
        try {
            Optional.of(
                    restTemplate.getForObject("$productApiUrl/${id}/", ProductEntity.class))
        } catch (HttpServerErrorException ex) {
            throw new ProductServiceApiException("Servidor indisponível")
        } catch (HttpClientErrorException ex) {
            throw new ProductNotFoundException("Porduto não encontrado")
        }
    }

}

@Canonical
class ProductApiResponse {
    Metadata meta
    List<ProductEntity> products
}

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
class Metadata {
    @JsonProperty("page_number")
    int pageNumber

    @JsonProperty("page_size")
    int pageSize
}

@Table(name="products")
@Entity
@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
class ProductEntity {
    @Id
    String id
    BigDecimal price
    String reviewScore
    String title
    String brand
    String image
}