package com.example.codechallengeaomata.Network


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        val baseURL ="https://simplifiedcoding.net/demos/"

        fun getRetroInstanceMovie() :Retrofit
        {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // This line is added for RxJava
                .build()
        }

    }
}