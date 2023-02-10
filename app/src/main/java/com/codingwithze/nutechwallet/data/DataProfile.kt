package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class DataProfile(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String
)