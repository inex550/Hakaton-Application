package com.example.hakaton_bastion.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "http://celvin.ru/api/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

object BastionApi {
    val retrofitService: BastionService by lazy {
        retrofit.create(BastionService::class.java)
    }
}