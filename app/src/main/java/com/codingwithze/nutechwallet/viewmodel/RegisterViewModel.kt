package com.codingwithze.nutechwallet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.nutechwallet.data.ResponseData
import com.codingwithze.nutechwallet.data.User
import com.codingwithze.nutechwallet.di.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(var api : ApiService) : ViewModel() {
    private val _statusRegist = MutableLiveData<ResponseData>()
    val statusRegist : LiveData<ResponseData> = _statusRegist

    fun registerUser(user : User){
        api.registUser(user).enqueue(object : Callback<ResponseData>{
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
               if (response.isSuccessful){
                   val data = response.body()
                   if (data != null){
                       _statusRegist.postValue(data!!)
                   }
               }else{
                   Log.e("Error: ", "onFailure : ${response.message()}")
               }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
            }

        })
    }
}