package com.example.ice_pomelo.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object IcePomeloNetwork {
    private val userService = ServiceCreator.create<UserService>()

    suspend fun login(username: String, password: String) =
        userService.login(username, password).await()

    suspend fun register(
        username: String,
        password: String,
        email: String,
        verificationCode: String
    ) = userService.register(username, password, email, verificationCode).await()

    suspend fun sendCode(email: String) = userService.sendCode(email).await()

    suspend fun getUserInfo(uid: String, token: String) =
        userService.getUserInfo(uid, token).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}