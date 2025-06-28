package com.solodev.launchx.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solodev.launchx.domain.model.Rocket

@Composable
fun MeasureDetailTable(rocket: Rocket?) {

    // Each cell of a column must have the same weight. 
    val column1Weight = .6f // 30%
    val column2Weight = .7f // 70%
    val column3Weight = .7f // 70%

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // Here is the header
        Row {
            TableCell(text = "Height", weight = column1Weight)
            TableCell(text = "Diameter", weight = column2Weight)
            TableCell(text = "Mass", weight = column3Weight)
        }
        // Here are all the lines of your table.
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "${rocket?.height?.meters}m.", weight = column1Weight)
            TableCell(text = "${rocket?.diameter?.meters}m.", weight = column2Weight)
            TableCell(text = "${rocket?.mass?.kg}kg.", weight = column3Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "${rocket?.height?.feet}.ft", weight = column1Weight)
            TableCell(text = "${rocket?.diameter?.feet}.ft", weight = column2Weight)
            TableCell(text = "${rocket?.mass?.lb}.lb", weight = column3Weight)
        }

    }
}