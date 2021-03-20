package com.example.hakaton_bastion.models.request

import com.google.gson.annotations.SerializedName

data class PinCode(

    @SerializedName("pin_code")
    val pinCode: String
)