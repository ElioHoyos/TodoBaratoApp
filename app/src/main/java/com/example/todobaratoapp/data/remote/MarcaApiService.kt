package com.example.todobaratoapp.data.remote

import com.example.todobaratoapp.data.model.Marca
import retrofit2.http.GET

interface MarcaApiService {
    @GET("api/v1/marcas")
    suspend fun getMarcas(): List<Marca>
}