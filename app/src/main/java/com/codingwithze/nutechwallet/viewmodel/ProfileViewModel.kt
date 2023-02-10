package com.codingwithze.nutechwallet.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.nutechwallet.data.*
import com.codingwithze.nutechwallet.di.ApiService
import com.codingwithze.nutechwallet.data.UpdateProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(var api : ApiService): ViewModel()  {

    private val _profileUser = MutableLiveData<ResponseProfile>()
    val profileUser : LiveData<ResponseProfile> = _profileUser

    private val _updateProfile = MutableLiveData<ResponseData>()
    val updateProfile : LiveData<ResponseData> = _updateProfile

    fun getProfile(token : String){
        api.getProfile(token).enqueue(object : Callback<ResponseProfile>{
            override fun onResponse(
                call: Call<ResponseProfile>,
                response: Response<ResponseProfile>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _profileUser.postValue(data!!)
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun postUpdateProfile(token : String,firstName : String, lastName : String ){
        api.updateProfile(token, UpdateProfile(firstName,lastName)).enqueue(object : Callback<ResponseData>{
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _updateProfile.postValue(data!!)
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