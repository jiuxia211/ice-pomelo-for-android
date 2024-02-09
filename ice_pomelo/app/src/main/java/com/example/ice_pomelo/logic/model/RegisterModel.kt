package com.example.ice_pomelo.logic.model

import android.text.TextUtils

data class RegisterModel(
    val username: String,
    val password: String,
    val email: String,
    val verificationCode: String
) {
    fun isValid(): Boolean {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(
            email
        ) && !TextUtils.isEmpty(verificationCode)
    }
}
