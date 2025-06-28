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
fun StageDetailTable(rocket: Rocket?) {

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
            TableCell(text = "Stages", weight = column1Weight)
            TableCell(text = "First Stage", weight = column2Weight)
            TableCell(text = "Second Stage", weight = column3Weight)
        }
        // Here are all the lines of your table.
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "Engines", weight = column1Weight)
            TableCell(text = "${rocket?.firstStage?.engines}", weight = column2Weight)
            TableCell(text = "${rocket?.secondStage?.engines}", weight = column3Weight)
        }
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "Thrust", weight = column1Weight)
            TableCell(text = "${rocket?.firstStage?.thrustSeaLevel?.lbf}", weight = column2Weight)
            TableCell(text = "${rocket?.secondStage?.thrust?.lbf}", weight = column3Weight)
        }

        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "Fuel", weight = column1Weight)
            TableCell(text = "${rocket?.firstStage?.fuelAmountTons}", weight = column2Weight)
            TableCell(text = "${rocket?.secondStage?.fuelAmountTons}", weight = column3Weight)
        }

        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "Reusable", weight = column1Weight)
            TableCell(text = "${rocket?.firstStage?.reusable}", weight = column2Weight)
            TableCell(text = "${rocket?.secondStage?.reusable}", weight = column3Weight)
        }
    }
}