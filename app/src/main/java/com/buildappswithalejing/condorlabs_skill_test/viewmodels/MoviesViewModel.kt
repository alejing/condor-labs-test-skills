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

class MoviesViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    private val _photos = MutableLiveData<MoviesData>() // Tengo que quitar despues
    val photos: LiveData<MoviesData> = _photos // Tengo que quitar despues

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
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
            try{
                //val listResult = MoviesApi.retrofitService.getMovies()
                //_status.value = "Success: ${listResult.size} Mars photos retrieved"
                _photos.value = MoviesApi.retrofitService.getMovies()[0]
                Log.d("MoviesViewModel",_photos.value!!.imgSrcUrl)
                _status.value = "   First Mars image URL : ${_photos.value!!.imgSrcUrl}"
            }catch (e: Exception){
                _status.value = "Failure: ${e.message}"
            }

        }
    }

}