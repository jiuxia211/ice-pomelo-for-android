package com.example.ice_pomelo.ui.myself

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.ice_pomelo.logic.Repository
import com.example.ice_pomelo.logic.model.GetUserInfoModel

class MyselfViewModel : ViewModel() {

    private val getUserInfoModelLiveData = MutableLiveData<GetUserInfoModel>()

    val getUserInfoResponseLiveData = getUserInfoModelLiveData.switchMap { getUserInfoModel ->
        Repository.getUserInfo(getUserInfoModel.uid, getUserInfoModel.token)
    }

    fun getUserInfo(uid: String, token: String) {
        getUserInfoModelLiveData.value = GetUserInfoModel(uid, token)
    }
}