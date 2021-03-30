package com.example.hakaton_bastion.network

import com.example.hakaton_bastion.models.network.User
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface BastionService {

    @POST("auth.php")
    suspend fun signIn(pinCode: String): User
}