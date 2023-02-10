package com.codingwithze.nutechwallet.data

import com.google.gson.annotations.SerializedName

data class ResponseTransactionHistory(
    @SerializedName("data")
    val `data`: List<TransactioHistory>,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)
