package com.vanessa.store.product.repository

import com.vanessa.store.product.model.Product

interface ProductService {
    Product findById(String id)
}