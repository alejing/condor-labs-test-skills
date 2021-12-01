package com.buildappswithalejing.condorlabs_skill_test.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildappswithalejing.condorlabs_skill_test.network.MoviesApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

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
                val listResult = MoviesApi.retrofitService.getMovies()
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            }catch (e: Exception){
                _status.value = "Failure: ${e.message}"
            }

        }
    }

}