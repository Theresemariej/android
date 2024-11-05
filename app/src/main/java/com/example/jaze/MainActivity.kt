package com.example.jaze

import ActeursScreen
import SeriesScreen
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
class Acteurs

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

                //Screen(windowSizeClass, navController)
                Scaffold(
                    bottomBar = {
                        if (currentDestination?.hasRoute<Home>() == false) {
                            barreDuBas(currentDestination, navController)
                        }
            })


                { innerPadding ->
                    NavHost(
                        navController, startDestination = Home(),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<Films> { FilmsScreen(viewModel, navController) }
                        composable<Series> { SeriesScreen(viewModel, navController) }
                        composable<Acteurs> { ActeursScreen(viewModel, navController) }
                        composable<Home> { Screen(windowSizeClass, navController) }

                        composable<FilmInfos> { navBackStackEntry ->
                            val filmInfos : FilmInfos = navBackStackEntry.toRoute()

                            //on vérifie si l'id n'est pas vide

                                FilmInfosScreen(
                                    viewModel,
                                    navController,
                                    filmInfos.id
                                )

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
    NavigationBar {
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


