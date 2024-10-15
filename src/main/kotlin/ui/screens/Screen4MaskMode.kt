package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import resources.MyStrings

@Composable
fun ScreenMaskMode(
    countOfLetters: Int,
    onMaskButtonClick: (String) -> Unit
) {
    var mask by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = MyStrings.MODE_MASK,
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        MaskLetters(
            countOfLetters = countOfLetters,
            onMaskChanged = { updatedMask ->
                mask = updatedMask
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { onMaskButtonClick(mask) }
        ) {
            Text(
                text = MyStrings.SEARCH,
                fontSize = 36.sp
            )
        }
    }
}

@Composable
private fun MaskLetters(
    countOfLetters: Int,
    onMaskChanged: (String) -> Unit
) {
    // Список символов, введенных в каждое поле, инициализируется пустыми строками.
    val letters = remember { mutableStateListOf<String>().apply { repeat(countOfLetters) { add("") } } }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Создаем TextField для каждого символа
        repeat(countOfLetters) { index ->
            val cellSize: Int
            val fontSize: Int
            if (countOfLetters <= 6) {
                cellSize = 120
                fontSize = 60
            } else if (countOfLetters <= 8) {
                cellSize = 80
                fontSize = 40
            } else if (countOfLetters <= 11) {
                cellSize = 64
                fontSize = 28
            } else {
                cellSize = 48
                fontSize = 16
            }

            TextField(
                value = letters[index],
                onValueChange = { newValue ->
                    // Проверяем, что длина введенного текста не превышает одного символа.
                    if (newValue.length <= 1) {
                        // Обновляем конкретный символ в списке.
                        letters[index] = newValue

                        // Формируем маску и вызываем onMaskChanged для передачи родителю.
                        val mask = letters.joinToString(separator = "") { char ->
                            char.ifBlank { "*" }
                        }
                        onMaskChanged(mask)
                    }
                },
                placeholder = {
                    Text(
                        text = "*",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = fontSize.sp
                    )
                },
                modifier = Modifier
                    .padding(4.dp)
                    .size(cellSize.dp, cellSize.dp),
                textStyle = TextStyle(
                    fontSize = fontSize.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

