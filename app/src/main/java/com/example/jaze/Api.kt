package com.example.jaze

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/collection")
    suspend fun collection(
        @Query("api_key")  api_key: String,
        @Query("query") query: String
    ): Collection


        @GET("trending/movie/week")
        suspend fun lastmovies(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String
    ): ModelListFilms

    @GET("movie/{id}?append_to_response=credits")
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

      @GET("trending/tv/week")
        suspend fun lastseries(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String
    ): ModelListSeries

    @GET("search/tv")
    suspend fun requestedseries(
        @Query("api_key")  api_key: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): ModelListSeries

    @GET("tv/{id}?append_to_response=credits")
    suspend fun seriedetails(
        @Path("id") id_serie: Int,
        @Query("api_key")  api_key: String,
        @Query("language") language: String
    ): ModelSerie


    @GET("trending/person/week")
        suspend fun lastactors(
                @Query("api_key")  api_key: String, 
                @Query("language") language: String
    ): ModelListActeurs

    @GET("search/person")
    suspend fun requestedactors(
        @Query("api_key")  api_key: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): ModelListActeurs

}
