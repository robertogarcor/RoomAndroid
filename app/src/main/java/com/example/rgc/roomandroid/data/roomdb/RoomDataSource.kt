package com.example.rgc.roomandroid.data.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.rgc.roomandroid.data.domain.model.Movie
import com.example.rgc.roomandroid.data.domain.repository.LocalDataSource
import com.example.rgc.roomandroid.data.toDomainMovie
import com.example.rgc.roomandroid.data.toRoomMovie

class RoomDataSource(db : MovieDatabase) : LocalDataSource {

    private val movieDao =  db.movieDao()

    override fun getMovies(): LiveData<List<Movie>> {
        return movieDao.getAllMovies().map { movies -> movies.map { it.toDomainMovie() }}
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies.map { it.toRoomMovie() })
    }

    override suspend fun size(): Int = movieDao.countMovies()

}