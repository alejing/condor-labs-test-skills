package com.buildappswithalejing.condorlabs_skill_test.viewmodels


import android.util.Log
import androidx.lifecycle.*
import com.buildappswithalejing.condorlabs_skill_test.network.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.NumberFormat

enum class MoviesApiStatus { LOADING, ERROR, DONE }

class MoviesViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    // The internal MutableLiveData that stores a List of Movies
    private val _photos = MutableLiveData<List<Movie>>()
    val photos: LiveData<List<Movie>> = _photos

    // The internal MutableLiveData that stores a id of a particular Movie
    /**
    private val _idMovie = MutableLiveData<String>()
    val idMovie: LiveData<String> = _idMovie
    */

    // The internal MutableLiveData that stores only a movie
    private val _movie = MutableLiveData<DataOneMovie>()
    val movie: LiveData<DataOneMovie> = _movie

    // The internal MutableLiveData that stores the budget value to currency conversion
    private val _budget = MutableLiveData<Double>()
    val budget: LiveData<String> = Transformations.map(_budget) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // The internal MutableLiveData that stores a list of movies showed in the details view
    private val _savedMovies = MutableLiveData<List<CustomMovie>>()
    val savedMovies: MutableLiveData<List<CustomMovie>> = _savedMovies

    // The internal MutableLiveData that stores a id of a particular Movie
    private val _idMovie = MutableLiveData<Int>()
    val idMovie: LiveData<Int> = _idMovie

    private var listMovies = mutableListOf<CustomMovie>()

    /**
     * Call getPopularMovies() on init so we can display status immediately.
     */
    init {
        getPopularMovies()
    }

    /**
    fun setIdMovie(idMovie: String) {
        _idMovie.value = idMovie
    }
    */
    fun addToFavorites(){

        // find the movie to put as a favorite
        val position = savedMovies.value?.indexOfFirst {
            it.id == idMovie.value
        }
        // get the movie object with the position
        val tempMovie = listMovies[position!!]

        // change the value to true
        tempMovie.favorite = true

        // remove the movie of the list
        listMovies.removeAt(position)

        // add the new movie with the changes
        listMovies.add(tempMovie)
        // add the data again to the LiveData

        _savedMovies.value = listMovies

    }

    private fun movieNotExist(id: Int): Boolean{
        val position = savedMovies.value?.indexOfFirst {
            it.id == idMovie.value
        }
        if (position == -1) return true

        return false
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
                // get Data from the REST Service
                val listResult = MoviesApi.retrofitService.getMovie(idMovie)
                // put all the movie data
                _movie.value = listResult
                // put only the budget value and convert toDouble
                _budget.value = listResult.budget.toDouble()
                // put only the id movie to track them
                _idMovie.value = listResult.id
                // save a custom movie in a local list of movies
                val customMovie = CustomMovie(
                    id = listResult.id,
                    title = listResult.title,
                    overview = listResult.overview,
                    releaseDate = listResult.releaseDate,
                    budget = listResult.budget,
                    videoPath =  listResult.videos.results[0].key,
                    imagePath = listResult.posterPath,
                    favorite = false
                )
                // verify if the list is not empty or does not contain the movie yet
                if (listMovies.isEmpty() || movieNotExist(listResult.id))
                    listMovies.add(customMovie)
                // add the lis of movies to live data
                _savedMovies.value = listMovies

                Log.d("MoviesViewModel", _savedMovies.value.toString())
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                //Log.e("MoviesViewModel", e.toString())
                _status.value = MoviesApiStatus.ERROR
                _movie.value = null
            }
        }

    }
}