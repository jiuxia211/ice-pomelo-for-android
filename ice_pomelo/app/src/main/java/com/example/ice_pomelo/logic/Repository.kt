package com.example.ice_pomelo.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.ice_pomelo.logic.dao.LoginDao
import com.example.ice_pomelo.logic.dao.TokenDao
import com.example.ice_pomelo.logic.dao.UidDao
import com.example.ice_pomelo.logic.model.LoginModel
import com.example.ice_pomelo.logic.network.IcePomeloNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun saveLoginModel(loginModel: LoginModel) = LoginDao.saveLoginModel(loginModel)

    fun getSavedLoginModel() = LoginDao.getSavedLoginModel()

    fun isLoginModelSaved() = LoginDao.isLoginModelSaved()

    fun saveToken(token: String) = TokenDao.saveToken(token)

    fun getSavedToken() = TokenDao.getSavedToken()

    fun isTokenSaved() = TokenDao.isTokenSaved()

    fun saveUid(uid: String) = UidDao.saveUid(uid)

    fun getSavedUid() = UidDao.getSavedUid()

    fun isSavedUid() = UidDao.isUidSaved()
    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
        val result = try {
            val loginResponse = IcePomeloNetwork.login(username, password)
            Log.d("network", loginResponse.toString())
            if (loginResponse.base.code == 10000) {
                Result.success(loginResponse)
            } else {
                Result.failure(RuntimeException("err msg: ${loginResponse.base.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun register(
        username: String,
        password: String,
        email: String,
        verificationCode: String
    ) = liveData(Dispatchers.IO) {
        val result = try {
            val registerResponse =
                IcePomeloNetwork.register(username, password, email, verificationCode)
            Log.d("network", registerResponse.toString())
            if (registerResponse.base.code == 10000) {
                Result.success(registerResponse)
            } else {
                Result.failure(RuntimeException("err msg: ${registerResponse.base.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)

    }

    fun sendCode(email: String) = liveData(Dispatchers.IO) {
        val result = try {
            val sendCodeResponse =
                IcePomeloNetwork.sendCode(email)
            Log.d("network", sendCodeResponse.toString())
            if (sendCodeResponse.base.code == 10000) {
                Result.success(sendCodeResponse)
            } else {
                Result.failure(RuntimeException("err msg: ${sendCodeResponse.base.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun getUserInfo(uid: String, token: String) = liveData(Dispatchers.IO) {
        val result = try {
            val getUserInfoResponse = IcePomeloNetwork.getUserInfo(uid, token)
            Log.d("network", getUserInfoResponse.toString())
            if (getUserInfoResponse.base.code == 10000) {
                Result.success(getUserInfoResponse)
            } else {
                Result.failure(RuntimeException("err msg: ${getUserInfoResponse.base.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}