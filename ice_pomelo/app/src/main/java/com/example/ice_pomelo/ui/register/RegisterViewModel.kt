package com.example.ice_pomelo.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.ice_pomelo.logic.Repository
import com.example.ice_pomelo.logic.model.RegisterModel

class RegisterViewModel : ViewModel() {
    private val registerModelLiveData = MutableLiveData<RegisterModel>()

    val registerResponseLiveData = registerModelLiveData.switchMap { registerModel ->
        Repository.register(
            registerModel.username,
            registerModel.password,
            registerModel.email,
            registerModel.verificationCode
        )
    }

    fun register(registerModel: RegisterModel) {
        registerModelLiveData.value = registerModel
    }

    private val sendCodeLiveData = MutableLiveData<String>()

    val sendCodeResponseLiveData = sendCodeLiveData.switchMap { email ->
        Repository.sendCode(email)
    }

    fun sendCode(email: String) {
        sendCodeLiveData.value = email
    }
}