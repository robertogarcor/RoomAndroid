package com.example.rgc.roomandroid.ui.viewmodel

import androidx.lifecycle.*
import com.example.rgc.roomandroid.data.domain.model.Movie
import com.example.rgc.roomandroid.data.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository : MovieRepositoryImpl) : ViewModel() {

    val movies : LiveData<List<Movie>> get() = repository.getMovies()

    init {
        Thread.sleep(2000)
        notifyLastVisible(0)
    }

    fun notifyLastVisible(lastVisible : Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.checkRequiredNewData(lastVisible)
            }
        }

    }
}

/*
class FactoryMainViewModel(private val db : MovieDatabase) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(MovieRepositoryImpl(RoomDataSource(db))) as T

        }
}
*/

