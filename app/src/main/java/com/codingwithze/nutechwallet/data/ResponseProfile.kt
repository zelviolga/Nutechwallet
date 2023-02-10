package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class ResponseProfile(
    @SerializedName("data")
    val `data`: DataProfile,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)