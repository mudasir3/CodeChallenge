package com.example.codechallenge

import com.example.codechallenge.Model.SuperHeroModel
import com.example.codechallenge.Network.RetroInstance
import com.example.codechallenge.Network.RetroService
import com.example.codechallenge.ViewModel.MainActivityViewModel
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {


    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainActivityViewModel

    @Mock
    lateinit var apiService: RetroService

    var retrofit: Retrofit? = null
        @Inject set

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        apiService = retrofit!!.create(RetroService::class.java)
        mainViewModel = MainActivityViewModel()
    }

    @Test
    fun getAllMoviesTest() {
        runBlocking {
            Mockito.`when`(apiService.getHeroes().isEmpty)
                .thenReturn(Single.just(false))
            mainViewModel.getHeroList(apiService)
            val result = mainViewModel.bookList.value
            assertEquals(listOf<SuperHeroModel>(SuperHeroModel("movie", "", "new",0,"","","","")), result)
        }
    }


//    @Test
//    fun `empty movie list test`() {
//        runBlocking {
//            Mockito.`when`(mainRepository.getAllMovies())
//                .thenReturn(Response.success(listOf<SuperHeroModel>()))
//            mainViewModel.getHeroList(apiService)
//            val result = mainViewModel.bookList.value
//            assertEquals(listOf<SuperHeroModel>(), result)
//        }
//    }

}