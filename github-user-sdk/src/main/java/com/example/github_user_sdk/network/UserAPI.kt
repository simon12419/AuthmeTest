package com.example.github_user_sdk.network

import android.content.Context
import com.android.volley.Request
import com.example.github_user_sdk.network.base.ApiUrl
import com.example.github_user_sdk.network.base.BaseAPI
import java.util.*

class UserAPI(context: Context) : BaseAPI(context) {

    fun getUserDetail(username: String, resultListener: ResultListener) {
        val params = HashMap<String, String>()
        params["username"] = username
        val url = ApiUrl.BASE_URL + ApiUrl.renderString(ApiUrl.QUERY_USER_DETAIL, params)
        val stringRequest = createStringRequest(Request.Method.GET, url, null, null, resultListener)
        requestQueue.add(stringRequest)
    }

    fun getUserList(since: Int, perPage: Int, resultListener: ResultListener) {
        val url = ApiUrl.BASE_URL + ApiUrl.QUERY_USER_LIST +"?since=$since&per_page=$perPage"
        val stringRequest = createStringRequest(Request.Method.GET, url, null, null, resultListener)
        requestQueue.add(stringRequest)
    }

}
