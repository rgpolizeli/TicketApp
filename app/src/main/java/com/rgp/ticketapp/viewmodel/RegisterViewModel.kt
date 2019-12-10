package com.rgp.ticketapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rgp.ticketapp.model.Session
import com.rgp.ticketapp.view.validator.RegisterFormValidator
import com.rgp.ticketapp.webservice.api.AuthenticationAPI
import com.rgp.ticketapp.webservice.webclient.WebServerClient
import kotlinx.coroutines.Dispatchers

class RegisterViewModel() : ViewModel() {
    private var requestRegisterLiveData: LiveData<Session>? = null
    private val authenticationAPI: AuthenticationAPI = WebServerClient.authenticationAPI
    private val registerFormValidator = RegisterFormValidator()

    fun register(email: String, password: String, name: String): LiveData<Session> {
        return requestRegisterLiveData ?: liveData(Dispatchers.IO) {
            if (registerFormValidator.isEmailValid(email) &&
                registerFormValidator.isPasswordValid(password) &&
                registerFormValidator.isNameValid(name)
            ) {
                try {
                    val fetchSession =
                        authenticationAPI.register(email = email, password = password, name = name)
                    emit(fetchSession)
                } catch (e: Exception) {
                    println(e.cause)
                    //todo: emit register error
                }
            } else {
                //todo: emit register validation error
            }
        }
    }

}