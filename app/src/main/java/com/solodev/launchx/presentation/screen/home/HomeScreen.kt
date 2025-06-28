package com.solodev.launchx.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.solodev.launchx.presentation.component.BoxCircularIndicator
import com.solodev.launchx.presentation.component.RocketCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeState: HomeState,
    onCardRocketClick: (String) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF30495E),
                        Color(0xFF040608),
                    ),
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    title = {
                        Text(
                            text = "LaunchX",
                            fontSize = 20.sp,
                            color = Color.White
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
                            Text(
                                text = "Error: ${homeState.errorMessage}",
                                color = Color.White
                            )
                        }
                    }

                    else -> LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        item {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "Rockets",
                                fontSize = 25.sp,
                                color = Color.White)

                            LazyRow(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp)) {
                                items(homeState.rockets ?: emptyList()) { rocket ->
                                    RocketCard(
                                        rocket = rocket,
                                        onCardRocketClick = onCardRocketClick
                                    )
                                }
                            }

                            LazyRow(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp)) {
                                items(homeState.crews ?: emptyList()) { crew ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        SubcomposeAsyncImage (
                                            modifier = Modifier
                                                .size(110.dp)
                                                .padding(10.dp)
                                                .clip(CircleShape),
                                            model = crew.image,
                                            contentDescription = "Rocket Image",
                                            contentScale = ContentScale.Crop,
                                            loading = { BoxCircularIndicator() }
                                        )

                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                                            text = "${crew.name}",
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            maxLines = 2,
                                            textAlign = TextAlign.Center
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
}

