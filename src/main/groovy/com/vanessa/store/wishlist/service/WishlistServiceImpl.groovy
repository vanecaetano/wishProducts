package com.vanessa.store.wishlist.service

import com.vanessa.store.product.repository.ProductEntity

import com.vanessa.store.customer.exception.CustomerNotFoundException
import com.vanessa.store.customer.model.Customer
import com.vanessa.store.customer.model.Wishlist
import com.vanessa.store.customer.repository.CustomerEntity
import com.vanessa.store.customer.service.CustomerService
import com.vanessa.store.product.exception.ProductNotFoundException
import com.vanessa.store.product.service.ProductService
import com.vanessa.store.wishlist.exception.WishlistBadInformationException

import static com.vanessa.store.converter.ModelEntityConverter.convertCustomerToEntity
import static com.vanessa.store.converter.ModelEntityConverter.convertCustomerToModel
import static com.vanessa.store.converter.ModelEntityConverter.convertProductToEntity

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
            CustomerEntity customer = convertCustomerToEntity(customerService.findById(customerId))
            ProductEntity product = convertProductToEntity(productService.findProductById(productId))

            if (!customer.wishlist) customer.wishlist = []
            customer.wishlist.add(product)
            customerService
                    .updateWishList(convertCustomerToModel(customer))
                    .collect {buildWishlistOutput(it)}
                    .first()

        } catch(CustomerNotFoundException c) {
            throw new WishlistBadInformationException("Cliente não encontrado")
        } catch(ProductNotFoundException p) {
            throw new WishlistBadInformationException("Produto não encontrado")
        }
    }

    @Override
    Wishlist getWishList(Long customerId) {
        try {
            Customer customer = customerService.findById(customerId)
            buildWishlistOutput(customer)
        } catch(CustomerNotFoundException c) {
            throw new WishlistBadInformationException("Cliente não encontrado")
        }
    }

    @Override
    void removeProductFromWishlist(Long customerId, String productId) {
        try {
            CustomerEntity customer = convertCustomerToEntity(customerService.findById(customerId))
            ProductEntity product = new ProductEntity(id: productId)

            customer.wishlist.remove(product)
            customerService
                    .update(convertCustomerToModel(customer))
                    .collect {buildWishlistOutput(it)}
                    .first()

        } catch(CustomerNotFoundException c) {
            throw new WishlistBadInformationException("Cliente não encontrado")
        }
    }

    private static Wishlist buildWishlistOutput(Customer it) {
        new Wishlist(customerId: it.id,
                wishlist: it.wishlist
        )
    }

}
