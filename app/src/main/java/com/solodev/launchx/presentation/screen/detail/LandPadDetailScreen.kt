package com.solodev.launchx.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.presentation.component.BoxCircularIndicator
import com.solodev.launchx.presentation.component.EngineDetailTable
import com.solodev.launchx.presentation.component.MeasureDetailTable
import com.solodev.launchx.presentation.component.StageDetailTable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandPadDetailScreen(landpad: Landpad?, onBackButtonClick: () -> Unit) {

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
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF213440),
                            Color(0xFF040608),
                        ),
                    ),
                )
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    title = { Text(text = "${landpad?.name}") },
                    navigationIcon = {
                        IconButton(onClick = onBackButtonClick) {
                            Icon(
                                modifier = Modifier.size(34.dp),
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Back"
                            )
                        }
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
            )
            {
                LazyColumn(modifier = Modifier) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                model = landpad?.images?.large?.firstOrNull(),
                                contentDescription = "Rocket Image",
                                contentScale = ContentScale.Crop,
                                loading = { BoxCircularIndicator() }
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Top
                            ) {
                                Text(
                                    text = "Full Name: ${landpad?.fullName}",
                                    color = Color.White,
                                )
                                Text(
                                    text = "Status: ${landpad?.status}",
                                    color = Color.White,
                                )
                                Text(
                                    text = "Locality: ${landpad?.locality}",
                                    color = Color.White,
                                )
                                Text(
                                    text = "Region: ${landpad?.region}",
                                    color = Color.White,
                                )

                                Text(
                                    text = "Type: ${landpad?.type}",
                                    color = Color.White,
                                )
                            }

                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp,vertical = 5.dp),
                                text = "Details: ${landpad?.details}",
                                color = Color.White)

                            landpad?.landingAttempts?.let {
                                EngineDetailTable(landpad = landpad)
                            }
                        }
                    }
                }
            }
        }
    }
}