package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)