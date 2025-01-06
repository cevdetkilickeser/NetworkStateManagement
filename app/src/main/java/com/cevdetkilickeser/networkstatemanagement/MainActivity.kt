package com.cevdetkilickeser.networkstatemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cevdetkilickeser.networkstatemanagement.ui.theme.NetworkStateManagementTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory {
            addInitializer(MainViewModel::class) {
                MainViewModel(networkObserver = NetworkObserver(context = applicationContext))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkStateManagementTheme {
                val networkStatus by viewModel.networkStatus.collectAsStateWithLifecycle()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(
                            modifier = Modifier.fillMaxSize(),
                            networkStatus = networkStatus
                        )
                    }
                }
            }
        }
    }
}