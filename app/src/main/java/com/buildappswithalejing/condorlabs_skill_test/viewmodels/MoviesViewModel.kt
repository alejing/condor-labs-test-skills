package com.buildappswithalejing.condorlabs_skill_test.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildappswithalejing.condorlabs_skill_test.network.MoviesApi
import com.buildappswithalejing.condorlabs_skill_test.network.MoviesData
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MoviesApiStatus { LOADING, ERROR, DONE }

class MoviesViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    private val _photos = MutableLiveData<List<MoviesData>>() // Tengo que actualizar despues
    val photos: LiveData<List<MoviesData>> = _photos // Tengo que actualizar despues

    /**
     * Call getPopularMovies() on init so we can display status immediately.
     */
    init {
        getPopularMovies()
    }
    /**
     * Gets popular movies information from the API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getPopularMovies() {

        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try{
                //val listResult = MoviesApi.retrofitService.getMovies()
                //_status.value = "Success: ${listResult.size} Mars photos retrieved"
                _photos.value = MoviesApi.retrofitService.getMovies()
                _status.value = MoviesApiStatus.DONE
            }catch (e: Exception){
                _status.value = MoviesApiStatus.ERROR
                _photos.value = listOf()
            }

        }
    }

}