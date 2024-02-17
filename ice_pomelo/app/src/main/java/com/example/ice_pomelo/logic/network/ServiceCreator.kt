package com.example.ice_pomelo.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://118.89.86.120/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}