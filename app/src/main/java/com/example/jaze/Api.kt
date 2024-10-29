package com.example.jaze

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

        @GET("trending/movie/week")
        suspend fun lastmovies(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String
    ): ModelListFilms

    /*   @GET("seash/movie")
        suspend fun requestedmovies(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String,
                @Query("recherche") recherche: String
    ): ModelListFilms*/

         @GET("movie/{id}")
        suspend fun moviedetails(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String,
                @Query("id_film") id_film: Int
    ): ModelMovie



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
