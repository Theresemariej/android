package com.example.jaze

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
    fun FilmsScreen(ViewModel: MainViewModel) {


        val film by ViewModel.movies.collectAsState() //permet de collecter tous les films de movies

        LaunchedEffect(Unit)
        {
            ViewModel.getMovies()
        }

    }