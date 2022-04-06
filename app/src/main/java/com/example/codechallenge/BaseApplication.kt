package com.example.codechallenge

import android.app.Application
import com.example.codechallenge.Dagger.AppComponent
import com.example.codechallenge.Dagger.AppModule
import com.example.codechallenge.Dagger.DaggerAppComponent
import com.example.codechallenge.Dagger.NetworkModule


class BaseApplication : Application() {
    companion object {
        var networkComponent: AppComponent? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule("https://simplifiedcoding.net/demos/"))
            .build()
    }
}