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
import data.HowCommonLetterType
import data.LetterButtonModel
import resources.MyStrings

@Composable
fun Screen3SetMode(
    onButtonLetterClick: (LetterButtonModel) -> Unit,
    chosenLetters: Set<LetterButtonModel>,
    trigger: Boolean,
    onButtonNextClick: () -> Unit
) {
    Screen3Display(
        onButtonLetterClick = onButtonLetterClick,
        chosenLetters = chosenLetters,
        trigger = trigger,
        onButtonNextClick
    )
}

@Composable
private fun Screen3Display(
    onButtonLetterClick: (LetterButtonModel) -> Unit,
    chosenLetters: Set<LetterButtonModel>,
    trigger: Boolean,
    onButtonNextClick: () -> Unit
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
            text = MyStrings.MODE_SET,
            fontSize = 36.sp,
        )
        Text(
            text = MyStrings.CHOOSE_LETTERS,
            fontSize = 36.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = chosenLetters
                .filter { it.isPressed }
                .map { it.letter }
                .sorted()
                .joinToString(" "),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        LetterButtons(onButtonLetterClick, chosenLetters)
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
private fun LetterButtons(
    onButtonLetterClick: (LetterButtonModel) -> Unit,
    chosenLetters: Set<LetterButtonModel>
) {
    CreateButtons(
        onButtonLetterClick,
        chosenLetters,
        HowCommonLetterType.MOST_COMMON
    )
    CreateButtons(
        onButtonLetterClick,
        chosenLetters,
        HowCommonLetterType.USUALLY
    )
    CreateButtons(
        onButtonLetterClick,
        chosenLetters,
        HowCommonLetterType.RARE
    )
}


@Composable
private fun CreateButtons(
    onButtonLetterClick: (LetterButtonModel) -> Unit,
    chosenLetters: Set<LetterButtonModel>,
    commonType: HowCommonLetterType
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        chosenLetters.filter { it.type == commonType }.forEach {
            Button(
                onClick = { onButtonLetterClick(it) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (it.isPressed) {
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
                    text = "${it.letter}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
