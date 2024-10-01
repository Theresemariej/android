package com.example.jaze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.ui.Modifier
import com.example.jaze.ui.theme.JAZETheme

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


            }
            }
        }
    }

