package com.example.codechallengeaomata

import android.app.Application
import com.example.codechallengeaomata.Dagger.AppComponent
import com.example.codechallengeaomata.Dagger.DaggerAppComponent


class BaseApplication : Application() {
    companion object {
        var networkComponent: AppComponent? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .networkModule(NetworkModule("https://simplifiedcoding.net/demos/"))
            .build()
    }
}