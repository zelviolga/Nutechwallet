package com.codingwithze.nutechwallet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codingwithze.nutechwallet.proto.UserPreferencesRepository
import kotlinx.coroutines.launch

class ProtoViewModel(application: Application): AndroidViewModel(application) {

    private val repo = UserPreferencesRepository(application)
    val dataUser = repo.readProto.asLiveData()

    fun editData(token : String) = viewModelScope.launch{
        repo.saveData(token)
    }

    fun clearData() = viewModelScope.launch{
        repo.deleteData()
    }
}