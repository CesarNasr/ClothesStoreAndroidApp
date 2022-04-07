package com.example.clothesstoreapp.datasource.network.api

import com.example.clothesstoreapp.datasource.model.ApiEntry
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api Service class calling @GET , @POST,  @DELETE, @UPDATE, @EDIT etc ...
 */
interface ApiService {
    @GET("0f78766a6d68832d309d")
    suspend fun fetchProducts(): Response<ApiEntry>
}