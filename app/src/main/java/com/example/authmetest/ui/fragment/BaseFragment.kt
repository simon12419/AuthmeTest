package com.example.authmetest.ui.fragment

import androidx.fragment.app.Fragment
import com.example.authmetest.ui.activity.BaseActivity

open class BaseFragment : Fragment() {

    open fun loading() {
        loading(null)
    }

    open fun loading(message: String?) {
        if (activity is BaseActivity)
            (activity as BaseActivity).loading(message)
    }

    open fun hideLoading() {
        if (activity is BaseActivity)
            (activity as BaseActivity).hideLoading()
    }

}
