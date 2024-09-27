package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import resources.MyStrings

const val MODE_SET = 1
const val MODE_MASK = 2

@Composable
fun ScreenChooseMode() {
    var mode by remember { mutableStateOf(1) }

    val onButtonClick: (Int) -> Unit = { modeButton ->
        mode = if (modeButton == MODE_SET) {
            MODE_SET
        } else {
            MODE_MASK
        }

    }

    ScreenChooseModeDisplay(onButtonClick, mode)
}

@Composable
private fun ScreenChooseModeDisplay(onButtonClick: (Int) -> Unit, mode: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = MyStrings.CHOOSE_MODE,
            textAlign = TextAlign.Center,
            fontSize = 48.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DisplayButtons(onButtonClick)
        }

        DisplayChosenMode(mode)
    }
}

@Composable
private fun DisplayButtons(onButtonClick: (Int) -> Unit) {
    for (i in 1..2) {
        Column(
            modifier = Modifier
                .padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val modeButton = if (i == MODE_SET) MODE_SET else MODE_MASK
            Button(
                onClick = { onButtonClick(modeButton) },
                modifier = Modifier
                    .padding(36.dp)
                    .size(160.dp)
                    .clip(CircleShape),
                elevation = ButtonDefaults.elevation(pressedElevation = 64.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = MyStrings.I_AM_BUTTON)
                    Text(text = MyStrings.CLICK_ME)
                }
            }

            val modeText = if (i == MODE_SET) MyStrings.CHOOSE_MODE_SET else MyStrings.CHOOSE_MODE_MASK
            Text(
                text = modeText,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
private fun DisplayChosenMode(mode: Int) {
    val modeString = if (mode == MODE_SET) MyStrings.CHOOSE_MODE_SET else MyStrings.CHOOSE_MODE_MASK
    Text(
        text = "Вы выбрали режим: $modeString",
        modifier = Modifier
            .padding(12.dp)
    )
}
