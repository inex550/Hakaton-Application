package com.example.hakaton_bastion.network

import com.example.hakaton_bastion.models.request.PinCode
import com.example.hakaton_bastion.models.response.User
import retrofit2.http.Body
import retrofit2.http.POST

interface BastionService {

    @POST("/auth.php")
    suspend fun signIn(@Body pinCode: PinCode): User
}