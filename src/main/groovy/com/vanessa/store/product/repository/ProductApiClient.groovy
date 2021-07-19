package com.vanessa.store.product.repository

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.product.exception.ProductServiceApiException
import com.vanessa.store.product.model.Product
import com.vanessa.store.product.service.ProductService
import groovy.transform.Canonical
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Canonical
class ProductApiClient implements ProductService {

    RestTemplate restTemplate
    String productApiUrl

    ProductApiClient(RestTemplate restTemplate, String productApiUrl) {
        this.restTemplate = restTemplate
        this.productApiUrl = productApiUrl
    }

    @Override
    Product findProductById(String id) {
        try {
            restTemplate.getForObject("$productApiUrl/${id}/", Product.class)
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
@JsonIgnoreProperties(ignoreUnknown = true)
class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    String id
    BigDecimal price
    String reviewScore
    String title
    String brand
    String image

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    BigDecimal getPrice() {
        return price
    }

    void setPrice(BigDecimal price) {
        this.price = price
    }

    String getReviewScore() {
        return reviewScore
    }

    void setReviewScore(String reviewScore) {
        this.reviewScore = reviewScore
    }

    String getTitle() {
        return title
    }

    void setTitle(String title) {
        this.title = title
    }

    String getBrand() {
        return brand
    }

    void setBrand(String brand) {
        this.brand = brand
    }

    String getImage() {
        return image
    }

    void setImage(String image) {
        this.image = image
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        ProductEntity that = (ProductEntity) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }


    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", reviewScore='" + reviewScore + '\'' +
                ", title='" + title + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}