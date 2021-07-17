package com.vanessa.store.product.service

import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.product.model.Product
import com.vanessa.store.product.repository.ProductEntity
import com.vanessa.store.product.repository.ProductService
import com.vanessa.store.product.repository.ProductRepository

class ProductServiceImp implements ProductService {

    ProductRepository productRepository

    ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository
    }

    @Override
    Product findById(String id) {
        productRepository.findProductById(id).map {
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
