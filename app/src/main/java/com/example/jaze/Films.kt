package com.example.jaze

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable



@Composable
fun FilmsScreen(ViewModel: MainViewModel,  navController: NavController) {

    val lesfilms by ViewModel.movies.collectAsState() //permet de collecter tous les films de movies
    var text by rememberSaveable { mutableStateOf("") }


    LaunchedEffect(Unit)
    {
        ViewModel.getMovies()
    }

    Column {

          SearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = { ViewModel.getSearchMovies(text)},
            placeholder = { Text("titre du film") },
            modifier = Modifier
                .height(70.dp)
                .align(Alignment.CenterHorizontally) // Aligne horizontalement
        ) { }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(16.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(lesfilms) { unfilm ->
                AfficherFilm(film = unfilm, navController = navController)
            }
        }
    }
}

@Composable
fun AfficherFilm(film: ModelFilm, navController: NavController) {
    Column( modifier = Modifier
        .padding(8.dp)
        .clickable {
            navController.navigate("FilmInfos/${film.id}")//on va dans la classe FilmInfos
        }
    ) {
        // Affichage des informations du film
        Text(text = film.title) // Affichage du titre du film

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${film.poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
        )
    }
}
