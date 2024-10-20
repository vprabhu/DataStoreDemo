package com.vpdevs.datastoredemo.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.userInfoDataStore: DataStore<Preferences> by preferencesDataStore(name = "userInfo")

class DSManager(private val contextParam: Context) {

    val userName = stringPreferencesKey("userName")
    val loginTimes = intPreferencesKey("loginTimes")

    fun getUserNameFromDataStore(): Flow<String> = contextParam.userInfoDataStore.data
        .map { preferences ->
            Log.d("datastoredemo", "getUserNameFromDataStore: ${preferences[userName]}")
            preferences[userName] ?: "Def"
        }

    fun getLoginTimesFromDataStore(): Flow<Int> = contextParam.userInfoDataStore.data
        .map { preferences ->
            Log.d("datastoredemo", "getLoginTimesFromDataStore: ${preferences[loginTimes]}")
            preferences[loginTimes] ?: -1
        }

    suspend fun saveUserName(name: String) {
        contextParam.userInfoDataStore.edit { preferences ->
            preferences[userName] = name
            Log.d("datastoredemo", "saveUserName: $name")
        }
    }

    suspend fun saveLoginTimes(times: Int) {
        contextParam.userInfoDataStore.edit { preferences ->
            preferences[loginTimes] = times
            Log.d("datastoredemo", "saveLoginTimes: $times")
        }
    }

}
