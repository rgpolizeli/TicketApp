package com.rgp.ticketapp


/**
 * This singleton provides the session for the authenticator interceptor.
 */
object SessionProvider {
    private var session: Session? = null

    fun getSession(): Session? {
        return session
    }

    fun setSession(newSession: Session?) {
        this.session = newSession
    }

}