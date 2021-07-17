package com.vanessa.store.product.service

import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.product.model.Product
import com.vanessa.store.product.repository.ProductApiClient
import com.vanessa.store.product.repository.ProductEntity
import com.vanessa.store.product.repository.ProductService
import com.vanessa.store.product.repository.ProductRepository

class ProductServiceImp implements ProductService {

    ProductRepository productRepository
    ProductApiClient productApiClient

    ProductServiceImp(ProductRepository productRepository, ProductApiClient productApiClient) {
        this.productRepository = productRepository
        this.productApiClient = productApiClient
    }

    @Override
    Product findById(String id) {
        productApiClient.findProductById(id).map {
            convert(it)
        }.orElseThrow {
            new ProductNotFoundException("Produto $id não encontrado")
        }
    }

    Product convert(ProductEntity product) {
        new Product(
                id: product.id,
                title: product.title,
                image: product.image,
                price: product.price,
                reviewScore: product.reviewScore
        )
    }
}
