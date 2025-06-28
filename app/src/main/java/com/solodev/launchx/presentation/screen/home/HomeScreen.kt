package com.solodev.launchx.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeState: HomeState,
    onCardRocketClick: (String) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LaunchX",
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            when {
                homeState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                homeState.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Error: ${homeState.errorMessage}")
                    }
                }

                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(homeState.rockets ?: emptyList()) { rocket ->

                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 5.dp,
                                    vertical = 5.dp)
                                .clickable{
                                    onCardRocketClick(rocket.id)
                                },
                            shape = RoundedCornerShape(5.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = rocket.flickrImages?.firstOrNull(),
                                    contentDescription = "Rocket Image",
                                    modifier = Modifier.size(180.dp)
                                )

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(text = "Name: ${rocket.name}")
                                    Text(text = "Type: ${rocket.type}")
                                    Text(text = "Cost Per Launch: ${rocket.costPerLaunch}")
                                }
                            }
                        }


                    }
                }
            }
        }
    }


}