package com.vanessa.store.converter

import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.repository.CustomerEntity
import com.vanessa.store.product.model.Product
import com.vanessa.store.product.repository.ProductEntity

class ModelEntityConverter {

    static CustomerEntity convertCustomerToEntity(Customer customer) {
        new CustomerEntity(
                id: customer.id,
                email: customer.email,
                name: customer.name,
                wishlist: customer.wishlist ? customer.wishlist.collect {convertProductToEntity(it)} : null
        )
    }

    static Customer convertCustomerToModel(CustomerEntity customer) {
        new Customer (
                id: customer.id,
                email: customer.email,
                name: customer.name,
                wishlist: customer.wishlist ? customer.wishlist.collect {convertProductToModel(it)} : null

        )
    }

    static ProductEntity convertProductToEntity(Product product) {
        new ProductEntity(
                id: product?.id,
                title: product?.title,
                image: product?.image,
                price: product?.price,
                reviewScore: product?.reviewScore
        )
    }

    static Product convertProductToModel(ProductEntity product) {
        new Product(
                id: product.id,
                title: product?.title,
                image: product?.image,
                price: product?.price,
                reviewScore: product?.reviewScore
        )
    }
}
