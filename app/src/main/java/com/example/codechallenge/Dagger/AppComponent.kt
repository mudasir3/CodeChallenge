package com.example.codechallenge.Dagger

import com.example.codechallenge.Activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity?)
}