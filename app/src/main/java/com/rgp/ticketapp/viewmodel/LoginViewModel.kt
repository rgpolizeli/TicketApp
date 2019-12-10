package com.rgp.ticketapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rgp.ticketapp.model.Session
import com.rgp.ticketapp.webservice.api.AuthenticationAPI
import com.rgp.ticketapp.webservice.webclient.WebServerClient
import kotlinx.coroutines.Dispatchers

class LoginViewModel() : ViewModel() {
    private var requestSessionLiveData: LiveData<Session>? = null
    private val authenticationAPI: AuthenticationAPI = WebServerClient.authenticationAPI

    fun authenticate(username: String, password: String): LiveData<Session> {
        return requestSessionLiveData ?: liveData(Dispatchers.IO) {
            if (username.isNotEmpty() || password.isNotEmpty()) {
                try {
                    val fetchSession =
                        authenticationAPI.login(username = username, password = password)
                    emit(fetchSession)
                } catch (e: Exception) {
                    println(e.cause)
                    //todo: emit login error
                }
            }
        }
    }

}