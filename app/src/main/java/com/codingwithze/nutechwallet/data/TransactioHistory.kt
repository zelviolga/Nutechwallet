package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class TransactioHistory(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("transaction_id")
    val transactionId: String,
    @SerializedName("transaction_time")
    val transactionTime: String,
    @SerializedName("transaction_type")
    val transactionType: String
)