package com.codingwithze.nutechwallet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.nutechwallet.data.ResponseData
import com.codingwithze.nutechwallet.data.TopUp
import com.codingwithze.nutechwallet.di.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TopupViewModel @Inject constructor(var api : ApiService) : ViewModel()  {
    private val _topup = MutableLiveData<ResponseData>()
    val topUp : LiveData<ResponseData> = _topup

    private val _transfer = MutableLiveData<ResponseData>()
    val transfer : LiveData<ResponseData> = _topup

    fun postTopUp(token : String,amount : TopUp){
        api.topUp(token,amount).enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _topup.postValue(data!!)
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

    fun postTransfer(token : String,amount : TopUp){
        api.transfer(token,amount).enqueue(object :Callback<ResponseData>{
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _topup.postValue(data!!)
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
                TODO("Not yet implemented")
            }

        })
    }
}