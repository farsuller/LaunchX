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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.utils.cardGradientBackground
import com.solodev.launchx.utils.clickableWithoutRipple

@Composable
fun LandPadCard(landpad: Landpad, onCardLandPadClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 14.dp, horizontal = 10.dp),
    ) {
        ElevatedCard(
            modifier = Modifier
                .size(120.dp)
                .clickableWithoutRipple {
                    onCardLandPadClick(landpad.id)
                }
                .padding(5.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(cardGradientBackground()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(0.8f),
                        text = "${landpad.name}",
                        color = Color.White,
                        fontSize = 17.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End
                    )

                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .weight(0.5f),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Forward Arrow",
                        tint = Color.White
                    )
                }
            }
        }

        SubcomposeAsyncImage(
            modifier = Modifier
                .offset((5).dp, (-30).dp)
                .size(110.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = landpad.images?.large?.firstOrNull(),
            contentDescription = "Rocket Image",
            contentScale = ContentScale.Crop,
            loading = { BoxCircularIndicator() }
        )
    }
}