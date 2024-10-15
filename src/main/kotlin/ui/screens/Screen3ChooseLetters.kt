package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.LetterButtonModel
import resources.MyStrings
import ui.SCREEN_SET_MODE

@Composable
fun ScreenSetMode(
    onButtonLetterClick: (LetterButtonModel) -> Unit,
    chosenLetters: Set<LetterButtonModel>,
    trigger: Boolean,
    onButtonNextClick: () -> Unit,
    screen: Int
) {
    val text: String
    val color: Color

    if (screen == SCREEN_SET_MODE) {
        text = MyStrings.MODE_SET
        color = Color.Green
    } else {
        text = MyStrings.MODE_EXLUDE_LETTERS
        color = Color.Red
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 36.sp,
        )
        Text(
            text = MyStrings.CHOOSE_LETTERS,
            fontSize = 36.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        CreateButtons(onButtonLetterClick, chosenLetters, color)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onButtonNextClick) {
            Text(
                text = MyStrings.NEXT,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CreateButtons(
    onLetterButtonClick: (LetterButtonModel) -> Unit,
    chosenLetters: Set<LetterButtonModel>,
    color: Color
) {
    val letterGroups = chosenLetters.chunked(8)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        letterGroups.forEach { group ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                group.forEach { letter ->
                    Button(
                        onClick = { onLetterButtonClick(letter) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (letter.isPressed) {
                                color
                            } else {
                                Color.Gray
                            }
                        ),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "${letter.letter}".uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
