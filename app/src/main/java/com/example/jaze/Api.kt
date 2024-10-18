package com.example.jaze

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

        @GET("trending/movie/week")
        suspend fun lastmovies(
                @Query("api_key") api_key: String
        ): ModelListFilms
}
