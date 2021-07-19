package com.vanessa.store.wishlist.service

import com.vanessa.store.product.repository.ProductEntity

import static com.vanessa.store.converter.ModelEntityConverter.convertToEntity
import com.vanessa.store.customer.exception.CustomerNotFoundException
import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.model.Wishlist
import com.vanessa.store.customer.repository.CustomerEntity
import com.vanessa.store.customer.service.CustomerService
import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.product.service.ProductService
import com.vanessa.store.wishlist.exception.WishlistBadInformationException

import static com.vanessa.store.converter.ModelEntityConverter.convertToModel

class WishlistServiceImpl implements WishlistService {
    CustomerService customerService
    ProductService productService

    WishlistServiceImpl(CustomerService customerService, ProductService productService) {
        this.customerService = customerService
        this.productService = productService
    }

    @Override
    Wishlist addProductToWishlist(Long customerId, String productId) {
        try {
            CustomerEntity customer = convertToEntity(customerService.findById(customerId))
            ProductEntity product = convertToEntity(productService.findProductById(productId))

            customer.wishlist.add(product)
            customerService
                    .update(convertToModel(customer))
                    .collect {buildWishlistOutput(it)}
                    .first()

        } catch(CustomerNotFoundException c) {
            throw new WishlistBadInformationException("Cliente não encontrado")
        } catch(ProductNotFoundException p) {
            throw new WishlistBadInformationException("Produto não encontrado")
        }
    }

    private static Wishlist buildWishlistOutput(Customer it) {
        new Wishlist(customerId: it.id,
                wishlist: it.wishlist
        )
    }

}
