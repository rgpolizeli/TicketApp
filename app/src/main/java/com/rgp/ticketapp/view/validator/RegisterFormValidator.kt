package com.rgp.ticketapp.view.validator

/**
 * This class validates the information given by the user on the registration form.
 */
class RegisterFormValidator {
    fun isEmailValid(email: String): Boolean {
        return email.isNotEmpty()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun isNameValid(name: String): Boolean {
        return name.isNotEmpty()
    }
}