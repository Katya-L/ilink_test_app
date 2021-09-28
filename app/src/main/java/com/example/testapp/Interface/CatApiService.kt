package com.example.testapp.Interface

import com.example.testapp.Models.CatModel
import retrofit2.Call
import retrofit2.http.GET

interface CatApiService {
    @GET("rest")
    fun getImage(): Call<CatModel>
}