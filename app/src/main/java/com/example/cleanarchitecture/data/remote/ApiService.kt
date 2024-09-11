package com.example.cleanarchitecture.data.remote

import com.example.cleanarchitecture.data.dto.DevelopersDTO
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("developers")
    suspend fun getDevelopers(@Query("language") language: String): List<DevelopersDTO>
}