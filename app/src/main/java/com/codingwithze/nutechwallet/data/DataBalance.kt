package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class DataBalance(
    @SerializedName("balance")
    val balance: Int
)