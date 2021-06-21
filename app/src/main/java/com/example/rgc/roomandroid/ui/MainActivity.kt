package com.example.rgc.roomandroid.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rgc.roomandroid.R
import com.example.rgc.roomandroid.data.domain.repository.MovieRepositoryImpl
import com.example.rgc.roomandroid.data.roomdb.RoomDataSource
import com.example.rgc.roomandroid.ui.adapter.MovieAdapter
import com.example.rgc.roomandroid.databinding.ActivityMainBinding
import com.example.rgc.roomandroid.ui.custom.app

import com.example.rgc.roomandroid.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding
    private val TAG =  "MainActivity_ITEMS_SCROLL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setTitle(R.string.app_main_name)

        viewModel = getViewModel { buildViewModel() }

        val movieAdapter = MovieAdapter(this)

        binding.recyclerviewMovie.adapter = movieAdapter

        viewModel.movies.observe(this, {movies -> movieAdapter.submitList(movies)})

        binding.recyclerviewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = recyclerView.layoutManager as GridLayoutManager
                Log.i(TAG, lm.findLastVisibleItemPosition().toString())
                val lastVisible = lm.findLastVisibleItemPosition()
                viewModel.notifyLastVisible(lastVisible)
            }
        })

        /**
         * Function Shape 2 -> listener : RecyclerView.OnScrollListener
         * Comment block -> binding.recyclerviewMovie.addOnScrollListener...
         */
        //lastVisibleEvents()

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    private fun buildViewModel() = MainViewModel(
        MovieRepositoryImpl(
            RoomDataSource(app.roomdb)
        )
    )

    private inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory : () -> T) : T {
        val vmf = object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = factory() as T
        }
        return ViewModelProvider(this, vmf).get()
    }

    /**
     * Shape 2 -> listener : RecyclerView.OnScrollListener
     */
    private fun lastVisibleEvents() {
        val listener = object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //Toast.makeText(this@MainActivity, "Scroll", Toast.LENGTH_LONG).show()
                val lm = recyclerView.layoutManager as GridLayoutManager
                Log.i(TAG, lm.findLastVisibleItemPosition().toString())
                val lastVisible = lm.findLastVisibleItemPosition()
                viewModel.notifyLastVisible(lastVisible)
            }
        }
        binding.recyclerviewMovie.addOnScrollListener(listener)
    }

}

