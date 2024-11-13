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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActeursScreen(ViewModel: MainViewModel,  navController: NavController, windowClass: WindowSizeClass) {

    val lesacteurs by ViewModel.actors.collectAsState()
    var text by rememberSaveable { mutableStateOf("") }
    var isSearchActive by rememberSaveable { mutableStateOf(false) } // État de la recherche

    val colonnes = when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> { 2}
        else -> 3
    }

    LaunchedEffect(Unit)
    {
        ViewModel.getActeurs()
    }


    Column {
        SearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = { isSearchActive = false; ViewModel.getSearchActeurs(text) },
            placeholder = { Text("Chercher") },
            active =  isSearchActive,
            onActiveChange = { isSearchActive = it },
            modifier = Modifier
                .padding(10.dp)
            //.height(90.dp)
        ) {

        }


        LazyVerticalGrid(
            columns = GridCells.Fixed(colonnes),
            modifier = Modifier.padding(16.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(lesacteurs) { unacteur ->
                AfficherActeurs(acteur = unacteur, navController = navController)
            }
        }
    }
}


@Composable
fun AfficherActeurs(acteur: ModelActeur, navController: NavController) {
    Column( modifier = Modifier
        .padding(8.dp)
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${acteur.profile_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
        )
        Text(text = acteur.name ?: "Nom non disponible",fontWeight = FontWeight.Bold)
    }
}
