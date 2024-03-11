package com.example.authmetest

import android.app.Application
import android.content.Context
import com.example.authmetest.ui.activity.MainViewModel
import com.example.github_user_sdk.repository.UserInfoRepository

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }

    private val repoModule = module {
        single { UserInfoRepository(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repoModule
                )
            )
        }
    }
}
