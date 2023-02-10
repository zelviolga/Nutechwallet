package com.codingwithze.nutechwallet.data


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String
)