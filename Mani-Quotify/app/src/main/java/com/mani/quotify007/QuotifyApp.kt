package com.mani.quotify007

import android.app.Application

class QuotifyApp: Application() {
    companion object {
        /* Hold a static reference to the AppModule instance, which can be accessed from anywhere
        * in the application without needing an instance of the Application class */
        lateinit var instance: QuotiyAppModule
    }
    override fun onCreate() {
        super.onCreate()
        instance = QuotiyAppModuleImpl(this)
    }
}