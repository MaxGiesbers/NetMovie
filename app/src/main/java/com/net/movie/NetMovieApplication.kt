package com.net.movie

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NetMovieApplication : Application()
{
    init
    {
        instance = this
    }

    companion object
    {
        private var instance: NetMovieApplication? = null

        fun applicationContext(): Context
        {
            return instance!!.applicationContext
        }
    }
}