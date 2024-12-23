package com.example.jaze

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jaze.MainViewModel

@Composable
fun NavScreen(viewModel: MainViewModel, navController: NavController, genree: String){
    val lesCol by viewModel.col.collectAsState() //permet de collecter tous les films de movies

    LaunchedEffect(genree)
    {
        viewModel.getCollection(genree)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(lesCol) { uneCol ->
            AfficherCollection(collect = uneCol, navController = navController)
        }
    }
}

@Composable
fun AfficherCollection(collect: Result, navController: NavController) {
 Column( modifier = Modifier
     .padding(8.dp)
 ){

     AsyncImage(
         model = "https://image.tmdb.org/t/p/w500${collect.poster_path}",
         contentDescription = null,
         contentScale = ContentScale.Crop,
         modifier = Modifier
             .fillMaxHeight()
             .width(250.dp)
     )
     Text(text= collect.name)
 }


}