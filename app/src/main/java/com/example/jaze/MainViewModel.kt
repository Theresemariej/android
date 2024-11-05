package com.example.jaze

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {

    val api_key = "b57151d36fecd1b693da830a2bc5766f"
    val language = "fr"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Api::class.java)
    // à partir de là, on peut appeler api.lastMovies(...)

    val movies = MutableStateFlow<List<ModelFilm>>(listOf())
    val movieById =  MutableStateFlow<ModelFilm?>(null)
    //val searchMovies = MutableStateFlow<List<ModelFilm>>(listOf())

    val series = MutableStateFlow<List<ModelSerie>>(listOf())
    val actors = MutableStateFlow<List<ModelActeur>>(listOf())



    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key, language).results
        }
    }

     fun getMovieById(id_film:Int) {
        viewModelScope.launch {
            Log.v("zzzzzz", id_film.toString())
            movieById.value = api.moviedetails(id_film,api_key, language)
        }
    }

     fun getSearchMovies(query: String) {
        viewModelScope.launch {
            movies.value = api.requestedmovies(api_key, language,query).results
        }
    }



/* 
     fun getSeries() {
        viewModelScope.launch {
            series.value = api.lastseries(api_key, language).results
       }
    }

    fun getActors() {
        viewModelScope.launch {
            actors.value = api.lastseries(api_key, language).results
        }
    }*/
}


