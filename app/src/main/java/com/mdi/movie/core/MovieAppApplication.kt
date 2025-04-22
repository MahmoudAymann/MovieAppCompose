package com.mdi.movie.core

import android.app.Application
import com.chat.chatsdk.ChatSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieAppApplication : Application(){


    override fun onCreate() {
        super.onCreate()
        ChatSdk.initialize(this)
    }
}