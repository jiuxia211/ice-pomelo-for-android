package com.example.ice_pomelo.ui.myself

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ice_pomelo.databinding.FragmentMyselfBinding
import com.example.ice_pomelo.logic.Repository
import com.example.ice_pomelo.utils.showToast

class MyselfFragment : Fragment() {

    private var _binding: FragmentMyselfBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[MyselfViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyselfBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.getUserInfoResponseLiveData.observe(viewLifecycleOwner) {
            val result = it.getOrNull()
            if (result != null) {
                if (it.isSuccess) {
                    binding.username.text = result.user.name
                    Glide.with(this).load(result.user.avatar_url).centerCrop().into(binding.avatar)
                } else {
                    result.base.msg.showToast()
                }
            } else {
                it.exceptionOrNull()?.printStackTrace()
            }
        }

        initUserInfo()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUserInfo() {
        if (Repository.isSavedUid() && Repository.isTokenSaved()) {
            viewModel.getUserInfo(Repository.getSavedUid(), Repository.getSavedToken())
        } else {
            Log.d("user", "获取用户信息失败")
        }
    }
}