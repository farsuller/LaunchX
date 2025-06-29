package com.solodev.launchx.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.domain.model.Rocket

@Composable
fun EngineDetailTable(landpad: Landpad?) {

    // Each cell of a column must have the same weight. 
    val column1Weight = .4f // 30%
    val column2Weight = .4f // 70%

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // Here is the header
        Row {
            TableCell(text = "Landing Attempts", weight = column1Weight)
            TableCell(text = "Landing Successes", weight = column2Weight)

        }
        // Here are all the lines of your table.
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "${landpad?.landingAttempts}", weight = column1Weight)
            TableCell(text = "${landpad?.landingSuccesses}", weight = column2Weight)

        }

    }
}