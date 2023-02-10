package com.codingwithze.nutechwallet.proto

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.codingwithze.nutechwallet.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class UserPreferencesRepository(private val context : Context) {
    //    create data store proto
    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = "userDataWallet",
        serializer = UserPreferencesSerializer
    )

    //    save data to datastore proto
    suspend fun saveData(token : String) {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().setToken(token).build()

        }
    }


//    delete datastore proto

    suspend fun deleteData() {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().clearToken().build()
        }
    }

//    read data store proto

    val readProto: Flow<UserPreferences> = context.userPreferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("tag", "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }
}