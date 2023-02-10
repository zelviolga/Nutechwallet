package com.codingwithze.nutechwallet.di

import com.codingwithze.nutechwallet.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("registration")
    fun registUser(
        @Body body: User
    ):Call<ResponseData>

    @POST("login")
    fun auth(
        @Body body: Login
    ) : Call<ResponseLogin>

    @GET("getProfile")
    fun getProfile (
        @Header("Authorization") authorization: String
    ) : Call<ResponseProfile>

    @POST("updateProfile")
    fun updateProfile(
        @Header("Authorization") authorization: String,
        @Body body: UpdateProfile
    ) : Call<ResponseData>


    @POST("topup")
    fun topUp(
        @Header("Authorization") authorization: String,
        @Body body: TopUp
    ) : Call<ResponseData>

    @POST("transfer")
    fun transfer(
        @Header("Authorization") authorization: String,
        @Body body: TopUp
    ) : Call<ResponseData>


    @GET("balance")
    fun getBalance (
        @Header("Authorization") authorization: String
    ) : Call<Balance>

    @GET("transactionHistory")
    fun transactioHistory (
        @Header("Authorization") authorization: String
    ) : Call<ResponseTransactionHistory>



}