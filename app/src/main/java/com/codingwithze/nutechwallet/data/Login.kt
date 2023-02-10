package com.codingwithze.nutechwallet.data

import com.google.gson.annotations.SerializedName

class Login (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)