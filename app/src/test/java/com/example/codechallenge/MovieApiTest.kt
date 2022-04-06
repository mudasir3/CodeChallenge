package com.example.codechallenge

import com.example.codechallenge.Network.RetroService
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

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