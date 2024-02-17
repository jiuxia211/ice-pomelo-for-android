package com.example.ice_pomelo.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.ice_pomelo.IcePomeloApplication

object UidDao {
    private fun sharedPreferences() =
        IcePomeloApplication.context.getSharedPreferences("uid", Context.MODE_PRIVATE)

    fun isUidSaved() = sharedPreferences().contains("uid")

    fun saveUid(uid: String) {
        sharedPreferences().edit {
            putString("uid", uid)
        }
    }

    fun getSavedUid(): String {
        val uid = sharedPreferences().getString("uid", "")
        return uid.toString()
    }
}