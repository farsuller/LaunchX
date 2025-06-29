package com.solodev.launchx

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.solodev.launchx.connectivity.ConnectivityObserver
import com.solodev.launchx.di.initializeKoin
import com.solodev.launchx.presentation.screen.detail.LandPadDetailScreen
import com.solodev.launchx.presentation.screen.detail.RocketDetailScreen
import com.solodev.launchx.presentation.screen.home.HomeScreen
import com.solodev.launchx.presentation.screen.home.HomeViewModel
import com.solodev.launchx.route.Route
import com.solodev.launchx.ui.theme.LaunchXTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchXTheme {
                val navController = rememberNavController()
                val viewModel = koinViewModel<HomeViewModel>()
                val homeState by viewModel.homeState.collectAsState()
                val networkStatus by viewModel.networkStatus.collectAsState()
                val context = LocalContext.current

                LaunchedEffect(networkStatus) {
                    if (networkStatus == ConnectivityObserver.Status.Lost ||
                        networkStatus == ConnectivityObserver.Status.Unavailable
                    ) {
                        Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show()
                    }
                }

                LaunchedEffect(Unit) {
                    viewModel.observeConnectivity()
                    viewModel.requestApi()
                }

                NavHost(
                    navController = navController,
                    startDestination = Route.Home
                ) {
                    composable<Route.Home> {
                        HomeScreen(
                            homeState = homeState,
                            onCardRocketClick = {
                                navController.navigate(Route.RocketDetail(it))
                            },
                            onCardLandPadClick = {
                                navController.navigate(Route.LandPadsDetail(it))
                            }
                        )
                    }

                    composable<Route.RocketDetail> { args ->
                        val argsDetail = args.toRoute<Route.RocketDetail>()
                        val rocket = homeState.rockets?.find { it.id == argsDetail.id }
                        RocketDetailScreen(
                            rocket = rocket,
                            onBackButtonClick = {
                                navController.navigateUp()
                            })
                    }

                    composable<Route.LandPadsDetail> { args ->
                        val argsDetail = args.toRoute<Route.LandPadsDetail>()
                        val landpad = homeState.landpads?.find { it.id == argsDetail.id }

                        LandPadDetailScreen(
                            landpad = landpad,
                            onBackButtonClick = {
                                navController.navigateUp()
                            })
                    }
                }
            }
        }
    }
}

