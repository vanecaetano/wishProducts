package com.vanessa.store.customer.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByEmail(String email)
}
