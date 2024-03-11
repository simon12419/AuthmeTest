package com.example.authmetest

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.volley.VolleyError
import com.example.github_user_sdk.network.UserAPI
import com.example.github_user_sdk.network.base.BaseAPI
import com.example.github_user_sdk.network.json.User
import com.example.github_user_sdk.network.json.UserDetail
import com.example.github_user_sdk.repository.UserInfoRepository
import io.mockk.CapturingSlot
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserInfoRepositoryTest {

    @MockK
    lateinit var userAPI: UserAPI

    @MockK
    lateinit var mockUserInfoRepository: UserInfoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        val context = InstrumentationRegistry.getInstrumentation().context
        userAPI = UserAPI(context)
        mockUserInfoRepository = UserInfoRepository(context)
    }

    @Test
    fun testGetUserDetail() = runBlocking {
        val listenerSlot = CapturingSlot<BaseAPI.ResultListener>()
        coEvery { userAPI.getUserDetail(any(), capture(listenerSlot)) } answers {
            listenerSlot.captured.onResult(MockResponse.userDetailJson)
        }

        mockUserInfoRepository.getUserDetail(
            userName = "userName",
            listener = object : UserInfoRepository.ResultListener<UserDetail> {
                override fun onResult(result: UserDetail) {
                    assertEquals("https://avatars.githubusercontent.com/u/1?v=4", result.avatar_url)
                    assertEquals("Tom Preston-Werner", result.name)
                    assertEquals("null", result.bio)
                    assertEquals("mojombo", result.login)
                    assertEquals(false, result.site_admin)
                    assertEquals("San Francisco", result.location)
                    assertEquals("http://tom.preston-werner.com", result.blog)
                }

                override fun onError(error: VolleyError) {
                    assert(false)
                }
            })
    }

    @Test
    fun testGetUserList() = runBlocking {
        val listenerSlot = CapturingSlot<BaseAPI.ResultListener>()
        coEvery { userAPI.getUserList(any(), any(), any()) } answers {
            listenerSlot.captured.onResult(MockResponse.userListJson)
        }

        mockUserInfoRepository.getUserList(
            since = 0,
            perPage = 20,
            listener = object : UserInfoRepository.ResultListener<List<User>> {
                override fun onResult(result: List<User>) {
                    assertEquals(3, result.size)
                    val user1 = result.firstOrNull()

                    assertEquals(1, user1?.id)
                    assertEquals("https://avatars.githubusercontent.com/u/1?v=4", user1?.avatar_url)
                    assertEquals("mojombo", user1?.login)
                    assertEquals(false, user1?.site_admin)
                }

                override fun onError(error: VolleyError) {
                    assert(false)
                }
            })
    }
}
