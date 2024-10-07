package ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import data.LetterButtonModel
import data.createLetters
import logic.getListOfWords
import resources.MyStrings
import ui.screens.*

const val MODE_SET = 1
const val MODE_MASK = 2

@Composable
fun App() {
    var screen by remember { mutableStateOf(1) }
    var wordLength by remember { mutableStateOf(0) }
    var words by remember { mutableStateOf<List<String>>(emptyList()) }
    var mode by remember { mutableStateOf(1) }

    val chosenLetters by remember { mutableStateOf(createLetters().toSet()) }
    val exludeLetters by remember { mutableStateOf(createLetters().toSet()) }
    var trigger by remember { mutableStateOf(false) }

    val onButtonLengthClick: (Int) -> Unit = { length ->
        wordLength = length
        words = getListOfWords(wordLength)
        screen++
    }

    val onButtonModeClick: (Int) -> Unit = { modeButton ->
        mode = if (modeButton == MODE_SET) {
            MODE_SET
        } else {
            MODE_MASK
        }
        screen++
    }

    val onButtonLetterClick: (LetterButtonModel) -> Unit = { letterButtonModel ->
        letterButtonModel.isPressed = !letterButtonModel.isPressed
        trigger = !trigger
    }

    val onButtonNextClick: () -> Unit = {
        screen++
    }

    when (screen) {
        1 -> Screen1WordLength(onButtonLengthClick)
        2 -> Screen2ChooseMode(onButtonModeClick, mode)
        3 -> Screen3SetMode(onButtonLetterClick, chosenLetters, trigger, onButtonNextClick)
        4 -> Screen4ExludeMode(onButtonLetterClick, exludeLetters, trigger, onButtonNextClick)
        5 -> {
            val chLetters = chosenLetters.filter { it.isPressed }.toSet()
            val exLetters = exludeLetters.filter { it.isPressed }.toSet()
            Screen5Result(wordLength, words, mode, chLetters, exLetters)
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = MyStrings.APP_TITLE,
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        resizable = false
    ) {
        App()
    }
}