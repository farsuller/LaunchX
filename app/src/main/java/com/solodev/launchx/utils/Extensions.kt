package com.solodev.launchx.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.solodev.launchx.ui.theme.GreenVogueColor
import com.solodev.launchx.ui.theme.LightNavyColor
import com.solodev.launchx.ui.theme.NightColor
import com.solodev.launchx.ui.theme.PickledWoodColor

@Composable
fun cardGradientBackground(): Brush = Brush.verticalGradient(
    colors = listOf(
        LightNavyColor,
        GreenVogueColor,
    ),
)

@Composable
fun gradientBackground(): Brush = Brush.verticalGradient(
    colors = listOf(
        PickledWoodColor,
        NightColor,
    ),
)

@Composable
fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit,
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.then(
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
        ),
    )
}