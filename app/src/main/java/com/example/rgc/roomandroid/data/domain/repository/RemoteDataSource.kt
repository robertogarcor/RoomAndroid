package com.example.rgc.roomandroid.data.domain.repository

import com.example.rgc.roomandroid.data.domain.model.Movie

interface RemoteDataSource {
    suspend fun getMovies() : List<Movie>
}