package com.example.rgc.roomandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.rgc.roomandroid.R
import com.example.rgc.roomandroid.databinding.ActivityMovieDetailBinding
import com.example.rgc.roomandroid.data.domain.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.act_toolbar_detail_movie_title)

        val movie = intent.getSerializableExtra("MOVIE") as Movie

        with(binding) {
            textViewTitleDetailMovie.text = movie.title
            textViewTypeDetailMovie.text = movie.type
            textViewDurationDetailMovie.text = movie.duration
            textViewDescriptionDetailMovie.text = movie.description
            Glide.with(this@MovieDetailActivity).load(movie.enclosure).into(imageViewDetailMovie);
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}