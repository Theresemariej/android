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



@Composable
fun FilmInfosScreen(ViewModel: MainViewModel,  navController: NavController, filmId: Int) {

     val film by ViewModel.movie.collectAsState()

    LaunchedEffect(filmId)
    {
        ViewModel.getMovieById(filmId)
    }



    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.spacedBy(20.dp)// Espacement entre chaque élément
    )
    {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = film.title)
        Image()   
        Informations()
        Acteurs()
}

        
    }

@Composable
fun Image(){
    AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${film.poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
        )

}

@Composable
fun Informations(){
    
        Text(text = "Genres")
        film.genre.forEach { Genre ->
        Text(text = Genre.name) // Supposons que chaque genre a un champ `name`
         }
        Text(text = film.overview)
        Text(text = film.original_language)
        Text(text = "Titre original: ${film.original_title}")

        //Text(text = "Nationalité: ${film.origin_country.joinToString(", ")}")
        Text(text = "Nationalité:")
        film.origin_country.forEach { country ->
        Text(text = country) // Supposons que chaque genre a un champ `name`
         }
}

@Composable
fun Acteurs(){
    azyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(16.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val lesActeurs=film.credits.cast.take(6)
            items(lesActeurs) { unActeur ->
           Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        navController.navigate("acteurDetails/${actor.id}")
                    }
            ) {
                
                //Image(painterResource(id = unActeur.profilePath), contentDescription = unActeur.name)
                Text(text = unActeur.name)
            }
        }
    }
}

