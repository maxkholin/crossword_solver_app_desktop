package ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import logic.createMostCommonLetters
import logic.createRareLetters
import logic.createUsuallyLetters


private val mostCommonLetters = createMostCommonLetters()
private val usuallyLetters = createUsuallyLetters()
private val rareLetters = createRareLetters()

@Composable
fun Screen3Ver2() {
    var isPressed by remember { mutableStateOf(false) }
    val chosenLetters by remember { mutableStateOf(mutableSetOf<Char>()) }

    val onButtonClick: (Char) -> Unit = { letter ->
        if (!chosenLetters.contains(letter)) {
            chosenLetters.add(letter)
        } else {
            chosenLetters.remove(letter)
        }
        isPressed = !isPressed
    }

    Screen3Display(onButtonClick, chosenLetters, isPressed)
}

@Composable
private fun Screen3Display(onButtonClick: (Char) -> Unit, chosenLetters: MutableSet<Char>, isPressed: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .border(1.dp, color = Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Отметьте нужные буквы",
            fontSize = 36.sp,
            modifier = Modifier
                .padding(bottom = 100.dp)
        )
        Text(
            text = "Вы добавили буквы:\n" + chosenLetters.joinToString(" ")
        )
        LetterButtons(onButtonClick, chosenLetters)
    }
}

@Composable
private fun LetterButtons(onButtonClick: (Char) -> Unit, chosenLetters: MutableSet<Char>) {
    CreateButtons(mostCommonLetters, onButtonClick, chosenLetters)
    CreateButtons(usuallyLetters, onButtonClick, chosenLetters)
    CreateButtons(rareLetters, onButtonClick, chosenLetters)
}


@Composable
private fun CreateButtons(
    letters: List<Char>,
    onButtonClick: (Char) -> Unit,
    chosenLetters: MutableSet<Char>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (letter in letters) {
            Button(
                onClick = { onButtonClick(letter) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (chosenLetters.contains(letter)) {
                        Color.Green
                    } else {
                        Color.Gray
                    }
                ),
                modifier = Modifier
                    .weight(1f, fill = false)
                    .padding(4.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "$letter",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
