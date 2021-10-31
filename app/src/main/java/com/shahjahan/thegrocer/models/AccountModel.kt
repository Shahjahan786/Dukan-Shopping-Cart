package com.shahjahan.thegrocer.models

data class AccountModel(
    val id: Int,
    val price: Int
) {
    val idText = "Order #$id"
    val priceText = "Rs.$price"
}