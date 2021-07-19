package com.vanessa.store.product.service


import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.product.model.Product
import com.vanessa.store.product.repository.ProductApiClient
import com.vanessa.store.product.repository.ProductRepository

class ProductServiceImp implements ProductService {

    ProductRepository productRepository
    ProductApiClient productApiClient

    ProductServiceImp(ProductRepository productRepository, ProductApiClient productApiClient) {
        this.productRepository = productRepository
        this.productApiClient = productApiClient
    }

    @Override
    Product findProductById(String id) {
        Product product = productApiClient.findProductById(id)
        if (!product) throw new ProductNotFoundException("Produto $id não encontrado")
        product
    }
}
