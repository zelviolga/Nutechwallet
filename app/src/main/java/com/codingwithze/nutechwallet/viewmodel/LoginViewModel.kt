package com.codingwithze.nutechwallet.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.nutechwallet.data.Login
import com.codingwithze.nutechwallet.data.ResponseData
import com.codingwithze.nutechwallet.data.ResponseLogin
import com.codingwithze.nutechwallet.di.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var api : ApiService):ViewModel() {



    private val _userLogin = MutableLiveData<ResponseLogin>()
    val userLogin : LiveData<ResponseLogin> = _userLogin

    fun authLogin(login : Login){
        api.auth(login).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _userLogin.postValue(data!!)
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }

            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }
}