package com.example.ice_pomelo

import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ice_pomelo.databinding.ActivityRegisterBinding
import com.example.ice_pomelo.logic.model.RegisterModel
import com.example.ice_pomelo.ui.register.RegisterViewModel
import com.example.ice_pomelo.utils.showToast

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var registerModel: RegisterModel

    private val viewModel by lazy { ViewModelProvider(this).get(RegisterViewModel::class.java) }

    private var isSendCodeButtonClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.sendCodeResponseLiveData.observe(this) {
            if (it.isSuccess) {
                "发送成功".showToast()
            } else {
                val result = it.getOrNull()
                if (result != null) {
                    result.base.msg.showToast()
                } else {
                    it.exceptionOrNull()?.printStackTrace()
                }
            }
        }

        binding.sendCodeButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editEmail.text.toString())) {
                viewModel.sendCode(binding.editEmail.text.toString())
            } else {
                "请输入邮箱".showToast()
            }
        }

        viewModel.registerResponseLiveData.observe(this) {
            if (it.isSuccess) {
                "注册成功".showToast()
                finish()
            } else {
                val result = it.getOrNull()
                if (result != null) {
                    result.base.msg.showToast()
                } else {
                    it.exceptionOrNull()?.printStackTrace()
                }
            }
        }
        binding.registerButton.setOnClickListener {
            registerModel = RegisterModel(
                binding.editUsername.text.toString(),
                binding.editPassword.text.toString(),
                binding.editEmail.text.toString(),
                binding.editVerificationCode.text.toString()
            )
            if (registerModel.isValid()) {
                viewModel.register(registerModel)
            } else {
                "格式错误".showToast()
            }
        }
        binding.goBackButton.setOnClickListener {
            finish()
        }
    }

    //倒计时，防止验证码短时间重复发送
    private fun startCountdown() {
        val timer = object : CountDownTimer(60L * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 更新按钮文本显示为倒计时的剩余秒数
                isSendCodeButtonClickable = false
                val secondsLeft = millisUntilFinished / 1000
                binding.sendCodeButton.text = "等待{$secondsLeft}秒"
            }

            override fun onFinish() {
                // 倒计时结束，恢复按钮为可点击状态
                isSendCodeButtonClickable = true
                binding.sendCodeButton.text = "发送验证码" // 恢复按钮文本
            }
        }
        timer.start()
    }
}