package com.example.codechallengeaomata.Dagger

import com.example.codechallengeaomata.Activity.MainActivity
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {
    fun inject(mainActivity: MainActivity?)
}