package com.codingwithze.nutechwallet.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.nutechwallet.data.ResponseData
import com.codingwithze.nutechwallet.data.ResponseProfile
import com.codingwithze.nutechwallet.data.ResponseTransactionHistory
import com.codingwithze.nutechwallet.di.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryViewModel  @Inject constructor(var api : ApiService) : ViewModel()  {
    private val _history = MutableLiveData<ResponseTransactionHistory>()
    val history : LiveData<ResponseTransactionHistory> = _history

    fun getTransactionHistory(token : String){
        api.transactioHistory(token).enqueue(object : Callback<ResponseTransactionHistory>{
            override fun onResponse(call: Call<ResponseTransactionHistory>, response: Response<ResponseTransactionHistory>) {
                if(response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _history.postValue(data)
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseTransactionHistory>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }



}