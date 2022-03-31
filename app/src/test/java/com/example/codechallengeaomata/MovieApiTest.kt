package com.example.codechallengeaomata

import androidx.lifecycle.Observer
import com.example.codechallengeaomata.Model.SuperHeroModel
import com.example.codechallengeaomata.Network.RetroService
import com.example.codechallengeaomata.ViewModel.MainActivityViewModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class MovieApiTest {
    private var mockWebServer = MockWebServer()

    private lateinit var apiService: RetroService

    @Before
    fun setUp() {
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mockWebServer.url("/")) // note the URL is different from production one
                .build()
                .create(RetroService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testCompleteIntegration() = runBlocking { // that will allow to wait for coroutine
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("""{
                    "imageurl":",
                }"""))

        val response = apiService.getHeroes()

        assertFalse("failed", false)
    }
}