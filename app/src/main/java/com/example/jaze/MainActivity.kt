package com.example.jaze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jaze.ui.theme.JAZETheme
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
class Films

@Serializable
class FilmInfos(val id: Int)

@Serializable
class Home

@Serializable
class Series

@Serializable
class SerieInfos(val id: Int)

@Serializable
class Acteurs

@Serializable
class ActeurInfos(val id: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JAZETheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Home(innerPadding)
                }*/
                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val viewModel: MainViewModel = viewModel()

                Scaffold(
                    bottomBar = {
                        if (currentDestination?.hasRoute<Home>() == false) {
                            when (windowClass.windowWidthSizeClass) {
                                WindowWidthSizeClass.COMPACT -> {
                            barreDuBas(currentDestination, navController)
                                }
                            }
                            else -> {}
                        }
            })


        { innerPadding ->

            Row( modifier = Modifier.fillMaxSize().padding(innerPadding) ) {
                if (currentDestination?.hasRoute<Home>() == false
                    && windowClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT
                ) {
                    barDuCote(navController, currentDestination)
                    //on met la barre du cote ici (dans le row) et pas dans le else d'en haut pasque c'est pas une bottomBar, et en haut, on veut que des bottomBar
                }

                Column {
                    NavHost(
                        navController, startDestination = Home(),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<Films> { FilmsScreen(viewModel, navController, windowSizeClass) }
                        composable<Series> { SeriesScreen(viewModel, navController) }
                        composable<Acteurs> { ActeursScreen(viewModel, navController) }
                        composable<Home> { Screen(windowSizeClass, navController) }

                        composable<FilmInfos> { navBackStackEntry ->
                            val filmInfos : FilmInfos = navBackStackEntry.toRoute()
                                FilmInfosScreen(
                                    viewModel,
                                    navController,
                                    filmInfos.id
                                )

                        }

                        composable<SerieInfos> { navBackStackEntry ->
                            val serieInfos : SerieInfos = navBackStackEntry.toRoute()
                            SerieInfosScreen(
                                viewModel,
                                navController,
                                serieInfos.id
                            )

                        }

                        composable<ActeurInfos> { navBackStackEntry ->
                            val acteurInfos : ActeurInfos = navBackStackEntry.toRoute()
                            SerieInfosScreen(
                                viewModel,
                                navController,
                                acteurInfos.id
                            )
                        }

                        }

                    }
                }
                
            }
        }
    }
}
}

@Composable
fun barreDuBas(currentDestination: NavDestination?,
               navController: NavController
){
    NavigationBar (
        containerColor = Color(255, 90, 180)
        ) {
        NavigationBarItem(
            icon = {
                Image(
                    painterResource(R.drawable.film),
                    contentDescription = "icon film",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
            }, label = { Text("Films") },
            selected = currentDestination?.hasRoute<Films>() == true,
            onClick = { navController.navigate(Films()) })


        NavigationBarItem(
            icon = {
                Image(
                    painterResource(R.drawable.film),
                    contentDescription = "icon film",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
            }, label = { Text("Series") },
            selected = currentDestination?.hasRoute<Series>() == true,
            onClick = { navController.navigate(Series()) })

        NavigationBarItem(
            icon = {
                Image(
                    painterResource(R.drawable.film),
                    contentDescription = "icon film",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
            }, label = { Text("Acteurs") },
            selected = currentDestination?.hasRoute<Acteurs>() == true,
            onClick = { navController.navigate(Acteurs()) })
    }
}



@Composable
fun barreDuCote(currentDestination: NavDestination?,
               navController: NavController
){
    NavigationRail(
        containerColor = Color(255, 90, 180),
        modifier = Modifier.fillMaxHeight()
    ) {
        NavigationRailItem(
            modifier = Modifier.weight(1f),
            icon = {
                Image(
                    painterResource(R.drawable.film),
                    contentDescription = "icon film",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
            }, label = { Text("Films") },
            selected = currentDestination?.hasRoute<Films>() == true,
            onClick = { navController.navigate(Films()) })


        NavigationRailItem(
            modifier = Modifier.weight(1f),
            icon = {
                Image(
                    painterResource(R.drawable.film),
                    contentDescription = "icon film",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
            }, label = { Text("Series") },
            selected = currentDestination?.hasRoute<Series>() == true,
            onClick = { navController.navigate(Series()) })

        NavigationRailItem(
            modifier = Modifier.weight(1f),
            icon = {
                Image(
                    painterResource(R.drawable.film),
                    contentDescription = "icon film",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
            }, label = { Text("Acteurs") },
            selected = currentDestination?.hasRoute<Acteurs>() == true,
            onClick = { navController.navigate(Acteurs()) })
    }
}

