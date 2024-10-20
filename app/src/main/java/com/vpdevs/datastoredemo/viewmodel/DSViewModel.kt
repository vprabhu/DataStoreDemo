package com.vpdevs.datastoredemo.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DSViewModel(application: Application) : AndroidViewModel(application) {

    private val _playerList = MutableStateFlow("")
    val playerList = _playerList.asStateFlow()

    val EXAMPLE_COUNTER = stringPreferencesKey("example_counter")

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    init {
        getValue()
    }

    fun getValue() {
        viewModelScope.launch {
            getUserFromPreferencesStore().collect { dataValue ->
                Log.d("datastoredemo", "getValue: $dataValue")
                _playerList.update {
                    dataValue
                }
            }
        }
    }

    private fun getUserFromPreferencesStore(): Flow<String> = context.dataStore.data
        .map { preferences ->
            Log.d("datastoredemo", "getUserFromPreferencesStore: ${preferences[EXAMPLE_COUNTER]}")
            preferences[EXAMPLE_COUNTER] ?: "Def"
        }

    fun incrementCounter(value: String) {
        viewModelScope.launch {
            context.dataStore.edit { settings ->
                _playerList.update {
                    value
                }
                settings[EXAMPLE_COUNTER] = value
                Log.d("datastoredemo", "incrementCounter: $value")
            }
        }
    }


}
