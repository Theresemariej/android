package com.example.jaze

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

        @GET("trending/movie/week")
        suspend fun lastmovies(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String
    ): ModelListFilms

    @GET("movie/{id}")
    suspend fun moviedetails(
        @Path("id") id_film: Int,
        @Query("api_key")  api_key: String,
        @Query("language") language: String
    ): ModelFilm

       @GET("search/movie")
        suspend fun requestedmovies(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String,
                @Query("query") query: String
    ): ModelListFilms


/*
      @GET("trending/tv/week")
        suspend fun lastseries(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String
    ): ModelListSeries

    @GET("trending/person/week")
        suspend fun lastseries(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String
    ): ModelListActeurs
    */
}
