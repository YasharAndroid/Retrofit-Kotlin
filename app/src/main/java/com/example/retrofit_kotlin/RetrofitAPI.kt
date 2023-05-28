package com.example.retrofit_kotlin

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/posts")
    fun getAllPost(): Call<List<Posts>>
}