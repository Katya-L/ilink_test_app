package com.example.testapp.Interface

import com.example.testapp.Models.DuckModel
import retrofit2.Call
import retrofit2.http.GET

interface DuckApiService {
    @GET("random")
    fun getImage(): Call<DuckModel>
}