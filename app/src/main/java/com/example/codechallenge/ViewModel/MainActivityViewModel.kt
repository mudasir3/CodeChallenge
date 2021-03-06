package com.example.codechallenge.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.Model.SuperHeroModel
import com.example.codechallenge.Network.RetroService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel:ViewModel() {



    lateinit var bookList :MutableLiveData<ArrayList<SuperHeroModel>>
    init {
        bookList = MutableLiveData()
    }

    fun getBookListObserver():MutableLiveData<ArrayList<SuperHeroModel>>{
        return bookList
    }

//    fun makeApiCall(query: String)
//    {
//        val retroInstance = RetroInstance.getRetroInstanceMovie().create(RetroService::class.java)
//        retroInstance.getBookListFromApi(query)
//            .subscribeOn(Schedulers.io()) // RxJava
//            .observeOn(AndroidSchedulers.mainThread())//RxJava
//            .subscribe(getBookListObserverRx())
//    }

    fun getHeroList(retroService:RetroService?)
    {
       // val retroInstance =retrofit!!.create(RetroService::class.java)

        //val retroInstance = RetroInstance.getRetroInstanceMovie().create(RetroService::class.java)
        retroService!!.getHeroes()
            .subscribeOn(Schedulers.io()) // RxJava
            .observeOn(AndroidSchedulers.mainThread())//RxJava
            .subscribe(getBookListObserverRx())
    }

    fun getBookListObserverRx(): Observer<ArrayList<SuperHeroModel>> {
        return object : Observer<ArrayList<SuperHeroModel>>{
            override fun onSubscribe(d: Disposable) {
                //start showing proress indicator

            }

            override fun onNext(t: ArrayList<SuperHeroModel>) {
                bookList.postValue(t)
            }

            override fun onError(e: Throwable) {
                bookList.postValue(null)
            }

            override fun onComplete() {
                //hide progress indicator
            }

        }
    }
}