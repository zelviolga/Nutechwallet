package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("data")
    val `data`: DataBalance,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int

)