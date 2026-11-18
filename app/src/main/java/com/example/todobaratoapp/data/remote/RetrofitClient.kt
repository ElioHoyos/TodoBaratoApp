package com.example.todobaratoapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    //10.0.2.2 apunta al localhost de tu pc cuando se corre el emulador de android studio
    //si se usa dispositivo fisico cambia por el IP de tu pc en la red local ej: 192.168.1.50:8081/
    private const val BASE_URL = "http://10.0.2.2:8081/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val marcaApiService: MarcaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarcaApiService::class.java)
    }
}