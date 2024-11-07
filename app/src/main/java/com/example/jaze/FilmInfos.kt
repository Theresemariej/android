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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun FilmInfosScreen(ViewModel: MainViewModel,  navController: NavController, filmId: Int) {

     val film by ViewModel.movieById.collectAsState()

    LaunchedEffect(filmId)
    {
        ViewModel.getMovieById(filmId)
    }



    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        film?.let { infos ->

            item(span = { GridItemSpan(2) }) {
                Column( verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(text = infos.title,
                        fontWeight = FontWeight.Bold, // Pour le gras
                        fontSize = 24.sp // Pour augmenter la taille
                    )
                    Image(infos)
                    Informations(infos)
                    Text(text = "Les Acteurs: ",fontWeight = FontWeight.Bold)

                }

            }



                val lesActeurs=infos.credits.cast.take(6)


                items(lesActeurs) { unActeur ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                navController.navigate("acteurDetails/${unActeur.id}")
                            }
                    ) {

                        //Image(painterResource(id = unActeur.profilePath), contentDescription = unActeur.name)
                        Photo(unActeur)
                        Text(text = unActeur.name)
                    }
                }
            }

        }
}

        


@Composable
fun Image(infos: ModelFilm){
    AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${infos.poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(250.dp)
        )

}

@Composable
fun Informations(infos: ModelFilm){

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Genres: ",fontWeight = FontWeight.Bold)
        infos.genres.forEach { genre ->
            Text(text = genre.name)
        }
    }
        Text(text = infos.overview)

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(text = "Titre original: ",fontWeight = FontWeight.Bold)
        Text(text= infos.original_title?: "")
    }

        //Text(text = "Nationalité: ${infos.origin_country.joinToString(", ")}")
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Nationalité: ", fontWeight = FontWeight.Bold)
        infos.origin_country.forEach { country ->
            Text(text = country)
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Langue originale: ", fontWeight = FontWeight.Bold)
        Text(text = infos.original_language)
    }
}


@Composable
fun Photo(acteur: Cast){
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${acteur.profile_path}",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.width(180.dp)
    )

}

