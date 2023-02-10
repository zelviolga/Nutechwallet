package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @SerializedName("data")
    val `data`: DataLogin,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)