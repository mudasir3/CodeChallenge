package com.example.codechallengeaomata.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codechallengeaomata.Adapter.BookListAdapter
import com.example.codechallengeaomata.Model.BookListModel
import com.example.codechallengeaomata.R
import com.example.codechallengeaomata.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var bookListAdapter: BookListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClickListeners()
        loadAPIData("q")

        recyclerViewfragment.apply {
            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 3, LinearLayoutManager.VERTICAL, false)
            this.layoutManager =gridLayoutManager
            bookListAdapter = BookListAdapter(this@MainActivity)
            adapter = bookListAdapter
        }
    }

    fun loadAPIData(query:String){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getBookListObserver().observe(this,object: Observer<BookListModel> {
            override fun onChanged(t: BookListModel?) {
                if(t !=null)
                {
                    bookListAdapter.bookListData =t.items
                    bookListAdapter.notifyDataSetChanged()
                }else
                {
                    Toast.makeText(this@MainActivity,"Error in fetching data", Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.makeApiCall(query)
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
            recyclerViewfragment.layoutManager =gridLayoutManager
        }

        rel_two.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_selected)
            txt_three.setTextAppearance(R.style.style_text_unselected)
            txt_four.setTextAppearance(R.style.style_text_unselected)
            txt_five.setTextAppearance(R.style.style_text_unselected)
            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 2, LinearLayoutManager.VERTICAL, false)
            recyclerViewfragment.layoutManager =gridLayoutManager
        }

        rel_three.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_unselected)
            txt_three.setTextAppearance(R.style.style_text_selected)
            txt_four.setTextAppearance(R.style.style_text_unselected)
            txt_five.setTextAppearance(R.style.style_text_unselected)
            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 3, LinearLayoutManager.VERTICAL, false)
            recyclerViewfragment.layoutManager =gridLayoutManager

        }

        rel_four.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_unselected)
            txt_three.setTextAppearance(R.style.style_text_unselected)
            txt_four.setTextAppearance(R.style.style_text_selected)
            txt_five.setTextAppearance(R.style.style_text_unselected)

            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 4, LinearLayoutManager.VERTICAL, false)
            recyclerViewfragment.layoutManager =gridLayoutManager
        }

        rel_five.setOnClickListener {
            txt_one.setTextAppearance(R.style.style_text_unselected)
            txt_two.setTextAppearance(R.style.style_text_unselected)
            txt_three.setTextAppearance(R.style.style_text_unselected)
            txt_four.setTextAppearance(R.style.style_text_unselected)
            txt_five.setTextAppearance(R.style.style_text_selected)

            val gridLayoutManager =
                GridLayoutManager(this@MainActivity, 5, LinearLayoutManager.VERTICAL, false)
            recyclerViewfragment.layoutManager =gridLayoutManager
        }
    }

}