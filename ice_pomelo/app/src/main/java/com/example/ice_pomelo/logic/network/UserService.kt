package com.example.ice_pomelo.logic.network

import com.example.ice_pomelo.logic.model.GetUserInfoResponse
import com.example.ice_pomelo.logic.model.LoginResponse
import com.example.ice_pomelo.logic.model.RegisterResponse
import com.example.ice_pomelo.logic.model.SendCodeResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @FormUrlEncoded
    @POST("pomelo/user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("pomelo/user/register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("verificationCode") verificationCode: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("pomelo/user/verification-code")
    fun sendCode(@Field("email") email: String): Call<SendCodeResponse>

    @GET("pomelo/user")
    fun getUserInfo(
        @Query("id") uid: String,
        @Header("Authorization") token: String
    ): Call<GetUserInfoResponse>
    
}