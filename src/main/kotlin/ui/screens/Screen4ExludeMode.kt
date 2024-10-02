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
import logic.HowCommonLetterType
import logic.LetterButtonModel
import logic.createLetters
import resources.MyStrings

@Composable
fun Screen4ExludeMode() {
    val exludeLetters by remember { mutableStateOf(createLetters().toSet()) }
    var trigger by remember { mutableStateOf(false) }

    val onButtonClick: (LetterButtonModel) -> Unit = { letterButtonModel ->
        if (!letterButtonModel.isPressed) {
            letterButtonModel.isPressed = true
        } else {
            letterButtonModel.isPressed = false
        }
        trigger = !trigger
    }

    Screen4Display(onButtonClick, exludeLetters, trigger)
}

@Composable
private fun Screen4Display(
    onButtonClick: (LetterButtonModel) -> Unit,
    exludeLetters: Set<LetterButtonModel>,
    trigger: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .border(1.dp, color = Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = MyStrings.MODE_EXLUDE_LETTERS,
            fontSize = 36.sp,
        )
        Text(
            text = MyStrings.CHOOSE_LETTERS,
            fontSize = 36.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = exludeLetters
                .filter { it.isPressed }
                .map { it.letter }
                .sorted()
                .joinToString(" "),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        LetterButtons(onButtonClick, exludeLetters)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {}){
            Text(
                text = MyStrings.NEXT,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun LetterButtons(
    onButtonClick: (LetterButtonModel) -> Unit,
    exludeLetters: Set<LetterButtonModel>
) {
    CreateButtons(
        onButtonClick,
        exludeLetters,
        HowCommonLetterType.MOST_COMMON
    )
    CreateButtons(
        onButtonClick,
        exludeLetters,
        HowCommonLetterType.USUALLY
    )
    CreateButtons(
        onButtonClick,
        exludeLetters,
        HowCommonLetterType.RARE
    )
}


@Composable
private fun CreateButtons(
    onButtonClick: (LetterButtonModel) -> Unit,
    exludeLetters: Set<LetterButtonModel>,
    commonType: HowCommonLetterType
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        exludeLetters.filter { it.type == commonType }.forEach {
            Button(
                onClick = { onButtonClick(it) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (it.isPressed) {
                        Color.Red
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
                    text = "${it.letter}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
