package com.shahjahan.thegrocer.repository

import com.shahjahan.thegrocer.db.database.GrocerDatabase
import com.shahjahan.thegrocer.models.AccountModel
import com.shahjahan.thegrocer.models.CartModel
import com.shahjahan.thegrocer.models.CategoryModel
import com.shahjahan.thegrocer.models.ProductModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.shahjahan.thegrocer.db.entities.Cart
import com.shahjahan.thegrocer.db.entities.Product
import com.shahjahan.thegrocer.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository(private val database: GrocerDatabase) {

    val cartForAccount: LiveData<List<CartModel>> =
        Transformations.map(database.mainDao.getCartForRecView()) { it.asCartModel() }
    val products: LiveData<List<ProductModel>> =
        Transformations.map(database.mainDao.getProducts()) { it.asProductModel() }
    val orders: LiveData<List<AccountModel>> =
        Transformations.map(database.mainDao.getAccount()) { it.asAccountModel() }

    val categories: LiveData<List<String>> = database.mainDao.getCategories();


    suspend fun refreshKitsAndItems() {
        withContext(Dispatchers.IO) {
            val productsList = Api.retrofitService.getAllProductsAsync().await()
            with(database.mainDao) {
                insertAllProducts(productsList.asProduct())

            }
        }
    }

    suspend fun add(id: Int) {
        withContext(Dispatchers.IO) {
            val productsForCart = database.mainDao.getCartForAccount()
            database.mainDao.insertInCart(productsForCart[id - 1])
        }
    }

    fun getProductById(id: Int): LiveData<Product> {

        return database.mainDao.getProductById(id)
    }

    suspend fun remove(id: Int) {
        withContext(Dispatchers.IO) {
            database.mainDao.remove(id)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            val listCart = database.mainDao.getCartForAccount()
            val price = listCart.sumByDouble { it.price.toDouble() }
            database.mainDao.insertAccount(price)
            delay(2200)
            database.mainDao.deleteAll()
        }
    }
}