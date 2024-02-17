package com.example.ice_pomelo.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.ice_pomelo.IcePomeloApplication

object TokenDao {
    private fun sharedPreferences() =
        IcePomeloApplication.context.getSharedPreferences("token", Context.MODE_PRIVATE)

    fun isTokenSaved() = sharedPreferences().contains("token")

    fun saveToken(token: String) {
        sharedPreferences().edit {
            putString("token", token)
        }
    }

    fun getSavedToken(): String {
        val token = sharedPreferences().getString("token", "")
        return token.toString()
    }
}