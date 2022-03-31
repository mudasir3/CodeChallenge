package com.example.codechallengeaomata.Network

import com.example.codechallengeaomata.Model.SuperHeroModel
import io.reactivex.Observable
import retrofit2.http.GET


interface RetroService {
    @GET("marvel")
    fun getHeroes(): Observable<ArrayList<SuperHeroModel>>
}