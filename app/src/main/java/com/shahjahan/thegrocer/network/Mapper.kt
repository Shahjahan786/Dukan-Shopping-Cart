package com.shahjahan.thegrocer.network

import com.shahjahan.thegrocer.db.entities.Account
import com.shahjahan.thegrocer.db.entities.Cart
import com.shahjahan.thegrocer.db.entities.Product
import com.shahjahan.thegrocer.models.AccountModel
import com.shahjahan.thegrocer.models.CartModel
import com.shahjahan.thegrocer.models.ProductModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class KitType(
    val id: String,
    var name: String,
    @Json(name = "image") val imgSrcUrl: String,
    val description: String,
    val price: String
)

@JsonClass(generateAdapter = true)
data class ItemType(
    val id: String,
    @Json(name = "item_name") val name: String,
    @Json(name = "item_image") val imgSrcUrl: String,
    val price: String
)

//type to database
fun List<ProductModel>.asProduct(): List<Product> {
    return map {
        Product(
            id = it.id,
            title = it.title,
            image = it.image,
            description = it.description,
            price = it.price,
            category = it.category
        )
    }
}


//for database to model(domain)
fun List<Product>.asProductModel(): List<ProductModel> {
    return map {
        ProductModel(
            id = it.id,
            title = it.title,
            image = it.image,
            description = it.description,
            price = it.price,
            category = it.category

        )
    }
}


//FOR CART::
fun CartModel.asCartType(): Cart {
    return Cart(
        id = id,
        title = title,
        image = image,
        price = price,
        description = description
    )
}

fun List<Cart>.asCartModel(): List<CartModel> {
    return map {
        CartModel(
            id = it.id,
            title = it.title,
            image = it.image,
            price = it.price,
            description = it.description
        )
    }
}

//For Account
fun List<Account>.asAccountModel(): List<AccountModel> {
    return map {
        AccountModel(
            id = it.id,
            price = it.price
        )
    }
}