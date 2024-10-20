package com.vpdevs.datastoredemo.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vpdevs.datastoredemo.data.DSManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewmodel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val dsManager = DSManager(context)

    private val _loginTimes = MutableStateFlow(0)
    val loginTimes = _loginTimes.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun getUserName() {
        viewModelScope.launch {
            dsManager.getUserNameFromDataStore().collect { value ->
                _userName.update {
                    value
                }
            }
        }
    }

    init {
        getLoginTimes()
    }

    private fun getLoginTimes() {
        viewModelScope.launch {
            dsManager.getLoginTimesFromDataStore().collect { value ->
                _loginTimes.update {
                    value
                }
            }
        }
    }

    fun saveUserName(userName: String) {
        viewModelScope.launch {
            dsManager.saveUserName(userName)
        }
    }

    fun saveLoginItems() {
        getLoginTimes()
        viewModelScope.launch {
            var currentTimes = _loginTimes.value
            currentTimes += 1
            dsManager.saveLoginTimes(currentTimes)
        }
    }
}
