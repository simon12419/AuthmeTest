package com.example.github_user_sdk.repository

import android.content.Context
import com.android.volley.VolleyError
import com.example.github_user_sdk.network.UserAPI
import com.example.github_user_sdk.network.base.BaseAPI
import com.example.github_user_sdk.network.json.User
import com.example.github_user_sdk.network.json.UserDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserInfoRepository(context: Context) {

    private val userAPI by lazy { UserAPI(context) }
    private val gson by lazy { Gson() }

    interface ResultListener<T> {
        fun onResult(result: T)
        fun onError(error: VolleyError)
    }

    suspend fun getUserDetail(userName: String, listener: ResultListener<UserDetail>) {
        userAPI.getUserDetail(userName, object : BaseAPI.ResultListener {
            override fun onResult(response: String?) {
                try {
                    val result = gson.fromJson(response, UserDetail::class.java)
                    listener.onResult(result)
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError(VolleyError(e.message))
                }
            }

            override fun onError(error: VolleyError) {
                listener.onError(error)
            }
        })
    }

    suspend fun getUserList(since: Int, perPage: Int, listener: ResultListener<List<User>>) {
        userAPI.getUserList(since, perPage, object : BaseAPI.ResultListener {
            override fun onResult(response: String?) {
                try {
                    val type = object : TypeToken<List<User>>() {}.type
                    val result = gson.fromJson<List<User>>(response, type)
                    listener.onResult(result)
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError(VolleyError(e.message))
                }
            }

            override fun onError(error: VolleyError) {
                listener.onError(error)
            }
        })
    }
}
