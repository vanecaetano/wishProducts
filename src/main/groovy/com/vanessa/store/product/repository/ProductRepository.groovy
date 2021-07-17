package com.vanessa.store.product.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository extends CrudRepository<ProductEntity, String> {

}