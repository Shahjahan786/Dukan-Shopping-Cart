package com.shahjahan.thegrocer.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shahjahan.thegrocer.db.entities.Product
import com.shahjahan.thegrocer.models.ProductModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fakestoreapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("products")
    fun getAllProductsAsync(): Deferred<List<ProductModel>>

    @GET("products/:id")
    fun getProduct(id: String): Deferred<ProductModel>

    @GET("products/categories")
    fun getAllCategories(): Deferred<List<String>>

}

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}