package com.example.myapplication

import com.example.myapplication.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @get:GET(value = "character")
    val posts : Call<Response?>?

    companion object{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}