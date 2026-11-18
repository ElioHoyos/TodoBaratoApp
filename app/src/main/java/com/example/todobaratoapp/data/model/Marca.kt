package com.example.todobaratoapp.data.model

import com.google.gson.annotations.SerializedName

data class Marca(
    @SerializedName("idmarca") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("estado") val estado: Boolean,
    @SerializedName("created_at") val created_at: String? = null,
    @SerializedName("updated_at") val updated_at: String? = null
)
