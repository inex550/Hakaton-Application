package com.example.hakaton_bastion.models.network

data class User(
    val fullname: String,
    val token: String,
    val pinCode: String,
    val status: Int
)