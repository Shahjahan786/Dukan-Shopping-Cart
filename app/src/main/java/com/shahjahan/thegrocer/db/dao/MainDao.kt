package com.shahjahan.thegrocer.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shahjahan.thegrocer.db.entities.Account
import com.shahjahan.thegrocer.db.entities.Cart
import com.shahjahan.thegrocer.db.entities.Product

@Dao
interface MainDao {

    @Query("select * from product")
    fun getProducts(): LiveData<List<Product>>

    @Query("select * from product where id=:id")
    fun getProductById(id: Int):LiveData<Product>

    @Query("select DISTINCT(category) from product")
    fun getCategories(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(products: List<Product>)


    @Query("select * from product")
    fun getCartForAccount(): List<Cart>

    @Query("select * from account")
    fun getAccount(): LiveData<List<Account>>

    @Query("select * from account")
    fun getAccountForDebug(): List<Account>

    @Query("insert into account values(null,:item)")
    fun insertAccount(item: Double)

    @Query("select * from product")
    fun getProductsForCart(): MutableList<Product>
    @Query("select * from cart")
    fun getCartForRecView(): LiveData<List<Cart>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInCart( cart: Cart)
    @Query("delete from cart where id=:id")
    fun remove(id: Int)
    @Query("delete from cart")
    fun deleteAll()



}