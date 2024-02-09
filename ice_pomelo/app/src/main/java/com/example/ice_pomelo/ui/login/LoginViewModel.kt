package com.example.ice_pomelo.ui.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.ice_pomelo.logic.Repository
import com.example.ice_pomelo.logic.model.LoginModel

class LoginViewModel : ViewModel() {

    private val loginModelLiveData = MutableLiveData<LoginModel>()

    // 在activity中观察loginResponseLiveData
    val loginResponseLiveData = loginModelLiveData.switchMap { loginModel ->
        Repository.login(loginModel.username, loginModel.password)
    }

    fun login(username: String, password: String) {
        loginModelLiveData.value = LoginModel(username, password, true)
    }

}