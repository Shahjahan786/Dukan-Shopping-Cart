package com.shahjahan.thegrocer.di

import android.content.Context
import androidx.room.Room
import com.shahjahan.thegrocer.db.dao.MainDao
import com.shahjahan.thegrocer.db.database.DB_NAME
import com.shahjahan.thegrocer.db.database.GrocerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideMainDao(appDatabase: GrocerDatabase): MainDao {
        return appDatabase.mainDao
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): GrocerDatabase {
        return Room.databaseBuilder(
            appContext,
            GrocerDatabase::class.java,
            DB_NAME
        ).build()
    }
}