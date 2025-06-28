package com.solodev.launchx.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.utils.clickableWithoutRipple

@Composable
fun RocketCard(rocket: Rocket, onCardRocketClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 14.dp, horizontal = 10.dp),
    ) {
        ElevatedCard(
            modifier = Modifier
                .size(140.dp)
                .clickableWithoutRipple {
                    onCardRocketClick(rocket.id)
                }
                .padding(5.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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

                val nameText = rocket.name?.let {
                    if (it.length > 10) it.take(10) + ".." else it
                } ?: ""
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                    text = nameText,
                    color = Color.White,
                    fontSize = 17.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }

        Icon(
            modifier = Modifier
                .offset((88).dp, (50).dp)
                .size(40.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Forward Arrow",
            tint = Color.White
        )

        SubcomposeAsyncImage(
            modifier = Modifier
                .offset((-18).dp, (-20).dp)
                .size(110.dp)
                .padding(10.dp)
                .clip(CircleShape),
            model = rocket.flickrImages?.firstOrNull(),
            contentDescription = "Rocket Image",
            contentScale = ContentScale.Crop,
            loading = { BoxCircularIndicator() }
        )
    }
}