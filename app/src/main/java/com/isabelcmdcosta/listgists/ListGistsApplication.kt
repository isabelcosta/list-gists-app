package com.isabelcmdcosta.listgists

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate

/**
 * This is the custom [Application]
 */

class ListGistsApplication : Application() {

    companion object {

        lateinit var instance: ListGistsApplication

        /**
         * @return the instance of the Application
         */
        fun getApplication(): ListGistsApplication {
            return instance
        }

        /**
         * @return the context of the Application
         */
        fun getContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}