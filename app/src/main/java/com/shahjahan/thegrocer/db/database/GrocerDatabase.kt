package com.shahjahan.thegrocer.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shahjahan.thegrocer.db.entities.Account
import com.shahjahan.thegrocer.db.entities.Cart
import com.shahjahan.thegrocer.db.entities.Product
import com.shahjahan.thegrocer.db.dao.MainDao
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Database(entities = [Product::class, Cart::class, Account::class], version = 1)
abstract class GrocerDatabase : RoomDatabase() {
    abstract val mainDao: MainDao
}

private lateinit var INSTANCE: GrocerDatabase

const val DB_NAME: String = "grocer_db"

fun getDatabase(context: Context): GrocerDatabase {
    synchronized(GrocerDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                GrocerDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
    return INSTANCE
}





