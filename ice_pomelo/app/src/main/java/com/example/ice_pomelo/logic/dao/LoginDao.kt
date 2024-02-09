package com.example.ice_pomelo.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.ice_pomelo.IcePomeloApplication
import com.example.ice_pomelo.logic.model.LoginModel
import com.google.gson.Gson

object LoginDao {
    private fun sharedPreferences() =
        IcePomeloApplication.context.getSharedPreferences("login", Context.MODE_PRIVATE)

    fun isLoginModelSaved() = sharedPreferences().contains("loginModel")

    fun saveLoginModel(loginModel: LoginModel) {
        sharedPreferences().edit {
            putString("loginModel", Gson().toJson(loginModel))
        }
    }

    fun getSavedLoginModel(): LoginModel {
        val loginModelJson = sharedPreferences().getString("loginModel", "")
        return Gson().fromJson(loginModelJson, LoginModel::class.java)
    }
}