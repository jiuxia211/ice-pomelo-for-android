package com.example.ice_pomelo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ice_pomelo.databinding.ActivityLoginBinding
import com.example.ice_pomelo.logic.Repository
import com.example.ice_pomelo.logic.model.LoginModel
import com.example.ice_pomelo.ui.login.LoginViewModel
import com.example.ice_pomelo.utils.showToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginModel: LoginModel
    private val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 自动登录判断
        // 不自动登录的情况:1.初次登录，未保存LoginModel 2. 从MainActivity返回，autoLogin为false
//        if (Repository.isLoginModelSaved()) {
//            loginModel = Repository.getSavedLoginModel()
//            if (loginModel.autoLogin) {
//                viewModel.login(loginModel.username, loginModel.password)
//            }
//        }

        viewModel.loginResponseLiveData.observe(this) {
            if (it.isSuccess) {
                Repository.saveLoginModel(loginModel)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
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

        binding.loginButton.setOnClickListener {
            loginModel = LoginModel(
                binding.editUsername.text.toString(),
                binding.editPassword.text.toString(),
                true
            )
            if (loginModel.isValid()) {
                viewModel.login(loginModel.username, loginModel.password)
            } else {
                "用户名或密码为空".showToast()
            }

        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}