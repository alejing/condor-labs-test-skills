package com.buildappswithalejing.condorlabs_skill_test.viewmodels


import android.util.Log
import androidx.lifecycle.*
import com.buildappswithalejing.condorlabs_skill_test.network.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

enum class MoviesApiStatus { LOADING, ERROR, DONE }

class MoviesViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    // The internal MutableLiveData that stores a List of Movies
    private val _photos = MutableLiveData<List<Movie>>()
    val photos: LiveData<List<Movie>> = _photos

    // The internal MutableLiveData that stores a id of a particular Movie
    private val _idMovie = MutableLiveData<String>()
    val idMovie: LiveData<String> = _idMovie

    // The internal MutableLiveData that stores only a movie
    private val _movie = MutableLiveData<DataOneMovie>()
    val movie: LiveData<DataOneMovie> = _movie


    /**
     * Call getPopularMovies() on init so we can display status immediately.
     */
    init {
        getPopularMovies()
    }

    fun setIdMovie(idMovie: String) {
        _idMovie.value = idMovie
    }

    fun getIdMovie() = _idMovie.value

    fun getOverview() = _movie.value?.overview
    fun getReleaseDate() = _movie.value?.releaseDate
    fun getBudget() = _movie.value?.budget

    //TODO Think the logic to find the first video key
    fun getVideo(): String{
        val urlYoutube = "https://www.youtube.com/watch?v=VSB4wGIdDwo"
        if(_movie.value?.videos?.results?.size != 0){
            Log.d("ModelViewModel", _movie.value?.videos?.results?.size.toString())
            //return urlYoutube + _movie.value?.videos?.results!![0].key
        }
        return urlYoutube
        //return "nv"
    }

    /**
     * Gets popular movies information from the API Retrofit service and updates the
     * getAllPopularMovies List LiveData
     */
    private fun getPopularMovies() {

        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try{
                _photos.value = MoviesApi.retrofitService.getAllMovies().results
                _status.value = MoviesApiStatus.DONE
            }catch (e: Exception){
                _status.value = MoviesApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

    fun getMovie(idMovie: String) {
        _status.value = MoviesApiStatus.LOADING
        viewModelScope.launch {
            try {
                val listResult = MoviesApi.retrofitService.getMovie(idMovie)
                _movie.value = listResult
                _status.value = MoviesApiStatus.DONE
                //Log.d("MoviesViewModel", listResult.videos.results[0].site)
                Log.d("MoviesViewModel", _status.value.toString())
            } catch (e: Exception) {
                Log.e("MoviesViewModel", e.toString())
                _status.value = MoviesApiStatus.ERROR
                _movie.value = null
            }
        }

    }
}