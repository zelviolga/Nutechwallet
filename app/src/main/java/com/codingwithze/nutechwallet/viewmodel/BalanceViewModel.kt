package com.codingwithze.nutechwallet.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.nutechwallet.data.Balance
import com.codingwithze.nutechwallet.data.ResponseProfile
import com.codingwithze.nutechwallet.di.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel@Inject constructor(var api : ApiService): ViewModel()  {

    private val _balance = MutableLiveData<Balance>()
    val balance : LiveData<Balance> = _balance

    fun getBalanceWallet(token : String){
        api.getBalance(token).enqueue(object : Callback<Balance>{
            override fun onResponse(call: Call<Balance>, response: Response<Balance>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _balance.postValue(data!!)
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Balance>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
            }


        })

    }
}