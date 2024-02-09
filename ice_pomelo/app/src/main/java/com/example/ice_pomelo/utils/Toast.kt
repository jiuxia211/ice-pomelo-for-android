package com.example.ice_pomelo.utils

import android.widget.Toast
import com.example.ice_pomelo.IcePomeloApplication

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(IcePomeloApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(IcePomeloApplication.context, this, duration).show()
}