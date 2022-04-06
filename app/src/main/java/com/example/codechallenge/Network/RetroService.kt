package com.example.codechallenge.Network

import com.example.codechallenge.Model.SuperHeroModel
import io.reactivex.Observable
import retrofit2.http.GET


interface RetroService {
    @GET("marvel")
    fun getHeroes(): Observable<ArrayList<SuperHeroModel>>
}