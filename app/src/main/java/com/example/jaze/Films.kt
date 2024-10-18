package com.example.jaze

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
    fun FilmsScreen(ViewModel: MainViewModel) {


        val film = ViewModel.movies.collectAsState() //permet de collecter tous les films de movies

        LaunchedEffect(Unit)
        {
            ViewModel.getMovies()
        }

    }