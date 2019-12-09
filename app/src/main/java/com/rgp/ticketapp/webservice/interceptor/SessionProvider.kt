package com.rgp.ticketapp.webservice.interceptor

import com.rgp.ticketapp.model.Session


/**
 * This singleton provides the session for the authenticator interceptor.
 */
object SessionProvider {
    private var session: Session? = null

    fun getSession(): Session? {
        return session
    }

    fun setSession(newSession: Session?) {
        session = newSession
    }

}