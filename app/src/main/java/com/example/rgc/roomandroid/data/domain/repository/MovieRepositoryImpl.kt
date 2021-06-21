package com.example.rgc.roomandroid.data.domain.repository

import androidx.lifecycle.LiveData
import com.example.rgc.roomandroid.data.domain.model.Movie
import com.example.rgc.roomandroid.data.serverdb.PreRetrofitDataSource

class MovieRepositoryImpl(private val localDataSource : LocalDataSource) {

    private val remoteDataSource : RemoteDataSource = PreRetrofitDataSource()

    companion object {
        const val ITEMS_LIMIT = 10
    }

    fun getMovies() : LiveData<List<Movie>> = localDataSource.getMovies()

    /*suspend fun getMovies() : List<Movie> {
        if(localDataSource.getMovies().isEmpty()) {
            val movies = remoteDataSource.getMovies()
            localDataSource.saveMovies(movies)
        }
        return localDataSource.getMovies()
    }*/

    suspend fun checkRequiredNewData(lastVisible : Int) {
        val size = localDataSource.size()
        if(lastVisible >= size - ITEMS_LIMIT + 1) {
            val newMovies = remoteDataSource.getMovies()
            localDataSource.saveMovies(newMovies)
        }
    }
}

/*
interface LocalDataSource {
    fun getMovies() : List<Movie>
    fun saveMovies(movies : List<Movie>)
}

interface RemoteDataSource {
    fun getMovies() : List<Movie>
}
*/








