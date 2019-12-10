package com.rgp.ticketapp.viewmodel

import android.content.Context
import com.rgp.ticketapp.model.Session

/**
 * This class performs interactions with Shared Preferences.
 */
object SharedPreferencesManager {
    private val SESSION_SHARED_PREFS = "SESSION_SHARED_PREFS"
    private val USERNAME_KEY = "USERNAMER_KEY"
    private val PASSWORD_KEY = "PASSWORD_KEY"
    private val USER_ID_KEY = "USER_ID_KEY"
    private val SESSION_TOKEN_KEY = "SESSION_TOKEN"

    fun getSessionFromPreferences(context: Context): Session? {
        var session: Session? = null
        val sharedPreferences =
            context.getSharedPreferences(SESSION_SHARED_PREFS, Context.MODE_PRIVATE)

        //todo: decrypt credentials.

        val userId: String = sharedPreferences.getString(USER_ID_KEY, "") ?: ""
        val token: String = sharedPreferences.getString(SESSION_TOKEN_KEY, "") ?: ""
        if (userId.isNotEmpty() && token.isNotEmpty()) {
            session = Session(userId = userId, token = token)
        }
        return session
    }

    fun saveSessionToPreferences(context: Context, session: Session) {
        val sharedPreferences =
            context.getSharedPreferences(SESSION_SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if (session.userId.isNotEmpty() && session.token.isNotEmpty()) {
            editor.putString(USER_ID_KEY, session.userId)
            editor.putString(SESSION_TOKEN_KEY, session.token)
            editor.apply();
        }
    }

    fun saveUserCredentialsToPreferences(context: Context, username: String, password: String) {
        val sharedPreferences =
            context.getSharedPreferences(SESSION_SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        //todo: encrypt credentials.

        editor.putString(USERNAME_KEY, username)
        editor.putString(PASSWORD_KEY, password)
        editor.apply();
    }
}