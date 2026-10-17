package com.timothy.week4_visualprogramming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.timothy.week4_visualprogramming.screen.MobilScreen
import com.timothy.week4_visualprogramming.screen.PartyScreen
import com.timothy.week4_visualprogramming.ui.theme.DarkBg
import com.timothy.week4_visualprogramming.ui.theme.ScreenBg
import com.timothy.week4_visualprogramming.ui.theme.Week4VisualProgrammingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var selectedTab by rememberSaveable { mutableStateOf(0) }

            Week4VisualProgrammingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = if (selectedTab == 0) ScreenBg else DarkBg,
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = selectedTab == 0,
                                onClick = { selectedTab = 0 },
                                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                                label = { Text("Jualan Mobil") }
                            )
                            NavigationBarItem(
                                selected = selectedTab == 1,
                                onClick = { selectedTab = 1 },
                                icon = { Icon(Icons.Filled.Star, contentDescription = null) },
                                label = { Text("Party Roster") }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        if (selectedTab == 0) {
                            MobilScreen()
                        } else {
                            PartyScreen()
                        }
                    }
                }
            }
        }
    }
}
