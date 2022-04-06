package com.example.codechallenge.Activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codechallenge.Adapter.BookListAdapter
import com.example.codechallenge.BaseApplication
import com.example.codechallenge.Model.SuperHeroModel
import com.example.codechallenge.Network.RetroService
import com.example.codechallenge.R
import com.example.codechallenge.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var bookListAdapter: BookListAdapter


    var retrofit: Retrofit? = null
        @Inject set

    var retroService:RetroService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        BaseApplication.networkComponent!!.inject(this@MainActivity)

        retroService =retrofit!!.create(RetroService::class.java)

        initClickListeners()
        loadAPIData("q")

        recyclerView.apply {
            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 3, LinearLayoutManager.VERTICAL, false)
            this.layoutManager =gridLayoutManager
            bookListAdapter = BookListAdapter(this@MainActivity)
            adapter = bookListAdapter
        }
    }

    fun loadAPIData(query: String){
        progressbar.visibility= View.VISIBLE

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getBookListObserver().observe(this, object : Observer<ArrayList<SuperHeroModel>> {
            override fun onChanged(t: ArrayList<SuperHeroModel>?) {
                if (t != null) {
                    bookListAdapter.bookListData = t
                    bookListAdapter.notifyDataSetChanged()

                    progressbar.visibility = View.GONE

                } else {
                    progressbar.visibility = View.GONE

                    Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        viewModel.getHeroList(retroService)
    }

    fun initClickListeners(){
        rel_one.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_selected)
            txt_two.setTextAppearance(R.style.style_text_unselected)
            txt_three.setTextAppearance(R.style.style_text_unselected)
            txt_four.setTextAppearance(R.style.style_text_unselected)
            txt_five.setTextAppearance(R.style.style_text_unselected)

            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 1, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager =gridLayoutManager
        }

        rel_two.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_selected)
            txt_three.setTextAppearance(R.style.style_text_unselected)
            txt_four.setTextAppearance(R.style.style_text_unselected)
            txt_five.setTextAppearance(R.style.style_text_unselected)
            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 2, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager =gridLayoutManager
        }

        rel_three.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_unselected)
            txt_three.setTextAppearance(R.style.style_text_selected)
            txt_four.setTextAppearance(R.style.style_text_unselected)
            txt_five.setTextAppearance(R.style.style_text_unselected)
            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 3, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager =gridLayoutManager

        }

        rel_four.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_unselected)
            txt_three.setTextAppearance(R.style.style_text_unselected)
            txt_four.setTextAppearance(R.style.style_text_selected)
            txt_five.setTextAppearance(R.style.style_text_unselected)

            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 4, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager =gridLayoutManager
        }

        rel_five.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_unselected)
            txt_three.setTextAppearance(R.style.style_text_unselected)
            txt_four.setTextAppearance(R.style.style_text_unselected)
            txt_five.setTextAppearance(R.style.style_text_selected)

            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 5, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager =gridLayoutManager
        }
    }

}