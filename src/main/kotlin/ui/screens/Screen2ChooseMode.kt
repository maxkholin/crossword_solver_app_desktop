package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import resources.MyStrings
import ui.MODE_MASK
import ui.MODE_SET

@Composable
fun ScreenChooseMode(
    onModeButtonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = MyStrings.CHOOSE_MODE,
            textAlign = TextAlign.Center,
            fontSize = 48.sp
        )
        Spacer(Modifier.height(36.dp))
        DisplayButtons(onModeButtonClick)
        DisplayDescription()
    }
}

@Composable
private fun DisplayButtons(
    onModeButtonClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 1..2) {
            val modeButton = if (i == MODE_SET) MODE_SET else MODE_MASK
            Button(
                onClick = { onModeButtonClick(modeButton) },
                modifier = Modifier
                    .padding(16.dp)
                    .size(200.dp)
                    .clip(CircleShape),
                elevation = ButtonDefaults.elevation(defaultElevation = 20.dp),
            ) {
                val modeText = if (i == MODE_SET) {
                    MyStrings.CHOOSE_MODE_SET
                } else {
                    MyStrings.CHOOSE_MODE_MASK
                }
                Text(
                    text = modeText,
                    fontSize = 24.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DisplayDescription() {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        for (i in 1..2) {
            val modeText = if (i == MODE_SET) {
                MyStrings.MODE_SET_DESCRIPTION
            } else {
                MyStrings.MODE_MASK_DESCRIPTION
            }
            Text(
                text = modeText,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
    }
}



