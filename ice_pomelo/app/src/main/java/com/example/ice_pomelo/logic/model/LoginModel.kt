package com.example.ice_pomelo.logic.model

import android.text.TextUtils

data class LoginModel(
    val username: String,
    val password: String,
    val autoLogin: Boolean
) {
    fun isValid(): Boolean {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
    }
}


