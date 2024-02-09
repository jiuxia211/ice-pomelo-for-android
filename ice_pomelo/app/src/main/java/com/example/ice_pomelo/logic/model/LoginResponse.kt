package com.example.ice_pomelo.logic.model

data class LoginResponse(
    val base: Base,
    val id: Int,
    val token: String
)