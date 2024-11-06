package com.example.jaze

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController


@Composable
fun ActeurInfosScreen(ViewModel: MainViewModel, navController: NavController, filmId: Int) {

    val film by ViewModel.movieById.collectAsState()

    LaunchedEffect(filmId)
    {
        ViewModel.getMovieById(filmId)
    }

}