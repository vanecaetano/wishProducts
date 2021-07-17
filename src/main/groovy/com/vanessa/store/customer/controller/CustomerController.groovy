package com.vanessa.store.customer.controller

import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.model.CustomerCreate
import com.vanessa.store.customer.service.CustomerService
import com.vanessa.store.product.repository.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@RestController
@RequestMapping("/customers")
class CustomerController {

    CustomerService customerService
    ProductService productService

    CustomerController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService
        this.productService = productService
    }

    @PostMapping
    ResponseEntity create(@RequestBody @Valid CustomerCreate customer) {
        ResponseEntity.status(CREATED)
                .body(customerService.create(customer))
    }

    @PutMapping
    ResponseEntity update(@RequestBody Customer customer) {
        ResponseEntity.status(OK)
                .body(customerService.update(customer));
    }

    @GetMapping("/{id}")
    ResponseEntity findById(@PathVariable Long id) {
        ResponseEntity.status(OK)
                .body(customerService.findById(id))
    }

    @GetMapping("/product/{id}")
    ResponseEntity findProduct(@PathVariable String id) {
        ResponseEntity.status(OK)
                .body(productService.findById(id))
    }

    @GetMapping
    ResponseEntity list() {
        ResponseEntity.status(OK)
                .body(customerService.findAll())
    }

    @GetMapping("/search")
    ResponseEntity findByEmail(@RequestParam String email) {
        ResponseEntity.status(OK)
                .body(customerService.findByEmail(email))
    }

    @DeleteMapping("/{id}")
    ResponseEntity remove(@PathVariable Long id) {
        ResponseEntity.status(OK)
                .body(customerService.remove(id))
    }
}