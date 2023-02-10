package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class TopUp(
    @SerializedName("amount")
    val amount: Int
)