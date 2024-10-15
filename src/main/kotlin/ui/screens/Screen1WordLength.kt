package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import resources.MyStrings

const val START_POSITION = 3

@Composable
fun ScreenWordLength(
    onLengthButtonClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = MyStrings.CHOOSE_LENGTH,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp)
        )
        ButtonGrid(onLengthButtonClick)
    }
}

@Composable
private fun ButtonGrid(
    onLengthButtonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 150.dp, end = 150.dp, top = 0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        var value = START_POSITION
        for (x in 1..3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (y in 1..4) {
                    val buttonValue = value
                    Button(
                        onClick = {
                            onLengthButtonClick(buttonValue)
                        },
                        modifier = Modifier.size(120.dp).padding(12.dp)
                    ) {
                        Text(
                            text = "$buttonValue",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    value++
                }
            }
        }
    }
}
