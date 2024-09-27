package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import resources.MyStrings
import logic.getListOfWords

/**
 * Композ-функция, отвечающая за экран Выбора Длины Слова.
 *
 * @param onButtonClick - функция обратного вызова, вызываемая при нажатии кнопки,
 *                       которая принимает длину слова и загружает соответствующий список слов.
 */
@Composable
fun ScreenWordLength() {
    var wordLength by remember { mutableStateOf(0) }
    var words by remember { mutableStateOf<List<String>>(emptyList()) }

    val onButtonClick: (Int) -> Unit = { length ->
        wordLength = length
        words = getListOfWords(wordLength)
    }

    ScreenWordLengthDisplay(onButtonClick = onButtonClick)
}

/**
 * Композ-функция, отвечающая за отрисовку основного экрана.
 *
 * Включает в себя:
 * - [Text] - элемент текста, предлагающий пользователю выбрать длину слова.
 * - [ButtonGrid] - функция, отображающая сетку из 16 кнопок для выбора длины слова.
 */
@Composable
private fun ScreenWordLengthDisplay(onButtonClick: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = MyStrings.PARSE_LENGTH_SECOND,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp)
        )
        ButtonGrid(onButtonClick)
    }
}

/**
 * Композ-функция, отображающая сетку из 16 кнопок.
 *
 * Каждая кнопка при нажатии вызывает коллбэк [onButtonClick] с соответствующим значением.
 * Это значение используется для загрузки списка слов из файла в зависимости от нажатой кнопки.
 *
 *  @param onButtonClick Лямбда-функция, принимающая целочисленное значение.
 *                      Вызывается при нажатии на кнопку, предоставляя длину слова.
 *
 */
@Composable
private fun ButtonGrid(
    onButtonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 150.dp, end = 150.dp, top = 0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        var value = 3
        for (x in 1..4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (y in 3..6) {
                    val buttonValue = value
                    Button(
                        onClick = {
                            onButtonClick(buttonValue)
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
