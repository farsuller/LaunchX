package com.solodev.launchx.presentation.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.solodev.launchx.domain.model.Rocket

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetailScreen(rocket: Rocket?, onBackButtonClick: () -> Unit){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Rocket Detail") },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = rocket?.flickrImages?.firstOrNull(),
                            contentDescription = "Rocket Image",
                            modifier = Modifier.size(140.dp),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(text = "Rocket Name: ${rocket?.name}")
                            Text(text = "Rocket Description: ${rocket?.description}")
                        }
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        items(rocket?.flickrImages?.drop(1) ?: emptyList()) { image ->
                            AsyncImage(
                                model = image,
                                contentDescription = "Rocket Image",
                                modifier = Modifier.size(140.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

    }


}