package com.vanessa.store.converter

import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.repository.CustomerEntity
import com.vanessa.store.product.model.Product
import com.vanessa.store.product.repository.ProductEntity

class ModelEntityConverter {

    static CustomerEntity convertToEntity(Customer customer) {
        new CustomerEntity(
                id: customer.id,
                email: customer.email,
                name: customer.name,
                wishlist: customer.wishlist.collect {convertToEntity(it)}
        )
    }

    static Customer convertToModel(CustomerEntity customer) {
        new Customer (
                id: customer.id,
                email: customer.email,
                name: customer.name,
                wishlist: customer.wishlist.collect {convertToModel(it)}

        )
    }

    static ProductEntity convertToEntity(Product product) {
        new ProductEntity(
                id: product.id,
                title: product.title,
                image: product.image,
                price: product.price,
                reviewScore: product.reviewScore
        )
    }

    static Product convertToModel(ProductEntity product) {
        new Product(
                id: product.id,
                title: product.title,
                image: product.image,
                price: product.price,
                reviewScore: product.reviewScore
        )
    }
}
