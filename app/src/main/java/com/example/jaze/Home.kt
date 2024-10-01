package com.example.jaze

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier

@Composable
fun Home(padding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
       horizontalAlignment = Alignment.CenterHorizontally) {

        Photo()
        Nom()
        Contact()
    }

}

@Composable
fun Screen(windowClass: WindowSizeClass) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Home(innerPadding)
            }

        }
        /*else -> {
            Row(Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(R.drawable.fleur),
                    contentDescription = "Des fleurs",
                )
                Text(modifier = Modifier.padding(10.dp),
                    text = "Description de l'image")
            }
        }*/
    }
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

