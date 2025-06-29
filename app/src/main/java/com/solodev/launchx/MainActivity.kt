package com.solodev.launchx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
        initializeKoin()
        setContent {
            LaunchXTheme {
                val navController = rememberNavController()

                val viewModel = koinViewModel<HomeViewModel>()
                val homeState by viewModel.homeState.collectAsState()

                LaunchedEffect(Unit) {
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

