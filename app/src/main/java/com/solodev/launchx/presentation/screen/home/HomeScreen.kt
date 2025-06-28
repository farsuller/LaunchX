package com.solodev.launchx.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.utils.clickableWithoutRipple

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
    ){
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor =  Color.Transparent),
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
                                color = Color.White)
                        }
                    }

                    else -> LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            LazyRow (modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)){
                                items(homeState.rockets ?: emptyList()) { rocket ->
                                    RocketCard(
                                        rocket = rocket,
                                        onCardRocketClick = onCardRocketClick
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
fun RocketCard(rocket: Rocket, onCardRocketClick: (String) -> Unit){

    Box(
        modifier = Modifier
            .padding(vertical = 14.dp, horizontal = 10.dp),
    ){
        ElevatedCard(
            modifier = Modifier
                .size(140.dp)
                .clickableWithoutRipple{
                    onCardRocketClick(rocket.id)
                }
                .padding(5.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF195188),
                                Color(0xFF0A2E54),
                            ),
                        )
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding
                            (bottom = 10.dp,
                            start = 10.dp,
                            end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val nameText = rocket.name?.let {
                        if (it.length > 10) it.take(8) + ".." else it
                    } ?: ""
                    Text(
                        text = nameText,
                        color = Color.White,
                        fontSize = 17.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Forward Arrow",
                        tint = Color.White
                    )
                }

            }
        }

        AsyncImage(
            modifier = Modifier
                .offset((-18).dp,(-20).dp)
                .size(110.dp)
                .padding(10.dp)
                .clip(CircleShape),
            model = rocket.flickrImages?.firstOrNull(),
            contentDescription = "Rocket Image",
            contentScale = ContentScale.Crop
        )
    }

}