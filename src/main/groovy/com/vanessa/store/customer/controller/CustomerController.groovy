package com.vanessa.store.customer.controller

import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.model.CustomerCreate
import com.vanessa.store.customer.service.CustomerService
import com.vanessa.store.product.service.ProductService
import com.vanessa.store.wishlist.service.WishlistService
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
    WishlistService wishlistService

    CustomerController(CustomerService customerService, ProductService productService, WishlistService wishlistService) {
        this.customerService = customerService
        this.productService = productService
        this.wishlistService = wishlistService
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

    @PostMapping("/{customerId}/wishlist/{productId}")
    ResponseEntity addProductToWishlist(@PathVariable Long customerId, @PathVariable String productId) {
        ResponseEntity.status(OK)
                .body(wishlistService.addProductToWishlist(customerId, productId)
                )
    }

//    @DeleteMapping("/{customerId}/wishList/{productId}")
//    ResponseEntity removeProduct(@PathVariable Long customerId, String productId) {
//        ResponseEntity.status(OK)
//                .body(customerService.removeProductFromWishlist(customerId, productId)
//                )
//    }
//
//    @GetMapping("/{customerId}/wishList")
//    ResponseEntity getWishlist(@PathVariable Long customerId) {
//        ResponseEntity.status(OK)
//                .body(customerService.getWishList(customerId)
//                )
//    }
}