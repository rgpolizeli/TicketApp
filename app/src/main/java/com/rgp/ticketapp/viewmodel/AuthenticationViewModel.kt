package com.rgp.ticketapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rgp.ticketapp.model.Session
import com.rgp.ticketapp.webservice.interceptor.SessionProvider

class AuthenticationViewModel(state: SavedStateHandle) : ViewModel() {

    companion object {
        private val USERID_KEY = "USERID_KEY"
        private val TOKEN_KEY = "TOKEN_KEY"
    }

    private val savedStateHandle = state

    fun saveSession(session: Session) {
        this.savedStateHandle.set(USERID_KEY, session.userId)
        this.savedStateHandle.set(TOKEN_KEY, session.token)
    }

    fun getSession(): Session? {
        val session: Session? = restoreSessionFromSavedStateHandle()
        if (SessionProvider.getSession() == null && session != null) {
            SessionProvider.setSession(session)
        }
        return session
    }

    private fun restoreSessionFromSavedStateHandle(): Session? {
        val userId: String? = this.savedStateHandle.get(USERID_KEY)
        val token: String? = this.savedStateHandle.get(TOKEN_KEY)
        return if (userId != null && token != null) {
            Session(userId, token)
        } else {
            null
        }
    }
}