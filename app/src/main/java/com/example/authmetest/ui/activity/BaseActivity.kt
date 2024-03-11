package com.example.authmetest.ui.activity

import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.authmetest.R
import com.example.authmetest.databinding.LayoutLoadingBinding

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "BaseActivity"
    }

    private val layoutLoadingBinding by lazy {
        val binding = LayoutLoadingBinding.inflate(layoutInflater)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        addContentView(binding.root, params)
        binding
    }

    open fun loading() {
        loading(null)
    }

    open fun loading(message: String?) {
        layoutLoadingBinding.rlLoading.visibility = View.VISIBLE
        layoutLoadingBinding.rlLoading.isClickable = true
        layoutLoadingBinding.pbMessage.text = message ?: getString(R.string.loading)
    }

    open fun hideLoading() {
        layoutLoadingBinding.rlLoading.visibility = View.GONE
    }

}
