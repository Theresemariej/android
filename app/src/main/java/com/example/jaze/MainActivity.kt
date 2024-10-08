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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jaze.ui.theme.JAZETheme
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlinx.serialization.Serializable


@Serializable
class Films

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
                Screen(windowSizeClass)


                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = {  Image(
                                    painterResource(R.drawable.film),
                                    contentDescription = "icon film",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                                       }, label = { Text("Mon profil") },
                                selected = currentDestination?.hasRoute<Films>() == true,
                                onClick = { navController.navigate(Films()) })
                        }
                    })
                { innerPadding ->
                    NavHost(
                        navController, startDestination = Films(),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<Films> { FilmsScreen() }
                    }
                }
            }
        }
    }
}
