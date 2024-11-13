package com.example.jaze

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController


@Composable
fun Home(padding: PaddingValues, navController: NavHostController) {
    Column(
       modifier = Modifier.fillMaxSize().padding(padding),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.spacedBy(20.dp)// Espacement entre chaque élément
    )
    {
        Spacer(modifier = Modifier.height(40.dp))
        Photo()
        Nom()
        Contact()
        Bouton(navController)
    }

}

@Composable
fun Screen(windowClass: WindowSizeClass, navController: NavHostController) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Home(innerPadding, navController)
            }
        }
        else -> { 
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Colonne pour la photo
                Column(
                    modifier = Modifier
                        .weight(1f) // Prend une part égale de l'espace
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally, // Centre horizontalement
                    verticalArrangement = Arrangement.Center // Centre verticalement
                ) {
                    Photo()
                }

                // Colonne pour le texte et le bouton
                Column(
                    modifier = Modifier
                        .weight(1f) // Prend une part égale de l'espace
                        .fillMaxHeight()
                        .padding(10.dp), // Espace autour de la colonne
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp) // Espacement entre les éléments
                ) {
                    Nom()
                    Contact()
                    Bouton(navController)
                } // Ferme le bloc de la colonne ici
            } // Ferme le bloc Row ici
        } // Ferme le bloc else ici
    } // Ferme le bloc when ici
}




@Composable
fun Photo(){
    Image(
        painterResource(R.drawable._128_evn_ssl2023_projection_ponyo),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )

}

@Composable
fun Nom(){
    Text(
        text = "Therese",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        modifier = Modifier.padding(80.dp)
    )
}

@Composable
fun Contact(){
    Text(
        text = "Therese@gmail.com",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun Bouton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate(FilmsDest()) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(250, 206, 230, 255) // Appliquer la couleur ici
        )

    ) {
        Text(text = "Afficher films")

    }
}
