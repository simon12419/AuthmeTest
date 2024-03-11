package com.example.authmetest.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.VolleyError
import com.example.github_user_sdk.network.json.User
import com.example.github_user_sdk.network.json.UserDetail
import com.example.github_user_sdk.repository.UserInfoRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class MainViewModel(private val userInfoRepository: UserInfoRepository) : ViewModel() {
    companion object {
        private const val PAGE_SIZE = 20
    }

    private var mLastUserId: Int = 0
    private var mIsGettingData = false //判斷請求任務是否進行中

    val userDetailResult: LiveData<UserDetail?>
        get() = _userDetailResult
    private val _userDetailResult = MutableLiveData<UserDetail?>()

    val userListResult: LiveData<List<User>?>
        get() = _userListResult
    private val _userListResult = MutableLiveData<List<User>?>()

    private val mUserList = mutableListOf<User>()

    fun getUserDetail(userName: String) {
        viewModelScope.launch {
            _userDetailResult.value = null
            userInfoRepository.getUserDetail(
                userName = userName,
                listener = object : UserInfoRepository.ResultListener<UserDetail> {
                    override fun onResult(result: UserDetail) {
                        _userDetailResult.postValue(result)
                    }

                    override fun onError(error: VolleyError) {
                        _userDetailResult.postValue(null)
                    }
                })
        }
    }

    //reset userList
    fun getFirstUserList() {
        mLastUserId = 0
        mUserList.clear()
        getMoreUserList()
    }

    //query next page userList
    fun getMoreUserList() {
        if (mUserList.size >= 100 || mIsGettingData)
            return
        mIsGettingData = true

        viewModelScope.launch {
            userInfoRepository.getUserList(
                since = mLastUserId,
                perPage = PAGE_SIZE,
                listener = object : UserInfoRepository.ResultListener<List<User>> {
                    override fun onResult(result: List<User>) {
                        result.lastOrNull()?.id?.let { id -> mLastUserId = id }

                        mUserList.addAll(result)
                        if (mUserList.size > 100) {
                            _userListResult.postValue(mUserList.subList(0, 100))
                        } else {
                            _userListResult.postValue(mUserList)
                        }
                        mIsGettingData = false
                    }

                    override fun onError(error: VolleyError) {
                        mIsGettingData = false
                    }
                }
            )
        }
    }
}
