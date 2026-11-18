package com.example.todobaratoapp.data.repository

import com.example.todobaratoapp.data.model.Marca
import com.example.todobaratoapp.data.remote.MarcaApiService
import com.example.todobaratoapp.data.remote.RetrofitClient

class MarcaRepository(private val apiService: MarcaApiService = RetrofitClient.marcaApiService) {
    suspend fun getMarcas(): List<Marca> = apiService.getMarcas();
}