package com.example.codechallengeaomata.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallengeaomata.Model.BookListModel
import com.example.codechallengeaomata.Network.RetroInstance
import com.example.codechallengeaomata.Network.RetroService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel:ViewModel() {
    lateinit var bookList :MutableLiveData<BookListModel>
    init {
        bookList = MutableLiveData()
    }

    fun getBookListObserver():MutableLiveData<BookListModel>{
        return bookList
    }

    fun makeApiCall(query: String)
    {
        val retroInstance = RetroInstance.getRetroInstanceMovie().create(RetroService::class.java)
        retroInstance.getBookListFromApi(query)
            .subscribeOn(Schedulers.io()) // RxJava
            .observeOn(AndroidSchedulers.mainThread())//RxJava
            .subscribe(getBookListObserverRx())
    }

    fun getBookListObserverRx(): Observer<BookListModel> {
        return object : Observer<BookListModel>{
            override fun onSubscribe(d: Disposable) {
                //start showing proress indicator
            }

            override fun onNext(t: BookListModel) {
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