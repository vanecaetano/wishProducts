package com.vanessa.store.wishlist.service

import com.vanessa.store.customer.model.Wishlist

interface WishlistService {
    Wishlist addProductToWishlist(Long customerId, String productId)
    Wishlist getWishList(Long customerId)
    void removeProductFromWishlist(Long customerId, String productId)
}